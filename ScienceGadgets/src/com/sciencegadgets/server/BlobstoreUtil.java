/*******************************************************************************
 *     This file is part of ScienceGadgets, a collection of educational tools
 *     Copyright (C) 2012-2015 by John Gralyan
 *
 *     ScienceGadgets is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *     ScienceGadgets is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 *     Contact us at info@sciencegadgets.org
 *******************************************************************************/
package com.sciencegadgets.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

public class BlobstoreUtil {

	private static final Logger log = Logger.getLogger(BlobstoreUtil.class
			.getName());

	/**
	 * Create a Blobstore URL which points to the upload servlet. If the servlet
	 * mapping is changed this URL must also change.
	 */
	public static String getUrl() {
		return BlobstoreServiceFactory.getBlobstoreService().createUploadUrl(
				"/upload");
	}

	public static String getFilename(BlobKey blobKey) {
		return new BlobInfoFactory().loadBlobInfo(blobKey).getFilename();
	}

	/**
	 * Extracts blob keys from a request, and returns them as a flat list.
	 */
	public static List<String> processRequest(HttpServletRequest req) {
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobKeyMap = blobstoreService
				.getUploads(req);
		List<String> blobKeyList = flattenBlobKeyMap(blobKeyMap);
		return blobKeyList;
	}

	/**
	 * Takes a Map of BlobKey lists returned by BlobstoreService.getUploads()
	 * and flattens them into one List.
	 */
	public static List<String> flattenBlobKeyMap(
			Map<String, List<BlobKey>> blobMap) {
		List<String> flatList = new LinkedList<String>();
		for (List<BlobKey> l : blobMap.values()) {
			for (BlobKey b : l) {
				flatList.add(b.getKeyString());
			}
		}
		return flatList;
	}

	// HOW TO DOWNLOAD/VIEW
	// String url = ("/serve?blob-key=" + x.getBlobKey());
	// Anchor a = new Anchor(url);

	/**
	 * Loads a list of BlobInfos from a list of blobkey Strings. Order is
	 * preserved.
	 * 
	 * @param keyStrings
	 *            - list of blobkeys as Strings
	 * @return - list of BlobInfos in the same order as the blob keys.
	 */
	public static List<BlobInfo> loadBlobInfos(List<String> keyStrings) {
		BlobInfoFactory infoFactory = new BlobInfoFactory();
		List<BlobInfo> blobInfos = new LinkedList<BlobInfo>();
		for (String keyString : keyStrings) {
			log.info("getting blob");
			BlobInfo blobInfo = infoFactory
					.loadBlobInfo(new BlobKey(keyString));
			log.info("got blob");
			if (blobInfo == null) {
				log.severe("Could not load BlobInfo from BlobKey " + keyString);
				continue;
			}
			blobInfos.add(blobInfo);
		}
		return blobInfos;
	}

	/**
	 * Deletes a Blob from the Blobstore.
	 * 
	 * @param s
	 *            - the Blob Key of the Blob to delete.
	 */
	public static void delete(String s) {
		BlobstoreServiceFactory.getBlobstoreService().delete(new BlobKey(s));
	}

	/**
	 * Gets the serving url of an image to serve from the blobstore using the
	 * string value of its blob key
	 * 
	 * @param blobKey
	 *            - The blob key of the image to serve as a string
	 * @return
	 */
	public static String getImageURL(BlobKey blobKey) {
		try {
			ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(
					blobKey).secureUrl(false);
			String servingUrl = ImagesServiceFactory.getImagesService()
					.getServingUrl(options);
			return servingUrl;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
