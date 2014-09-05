package com.sciencegadgets.server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

/**
 * This servlet is mapped to the callback URL used when generating the Blobstore upload URL. 
 * It Processes uploaded Blobs, adds them to the FileCollection, and notifies all clients of 
 * the change. 
 */
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
        
        private static Logger log = Logger.getLogger(UploadServlet.class.getName());
        
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        	
        	System.out.println("in doPost res1: "+response);
        	
//                List<String> uploadedKeys = BlobstoreUtil.processRequest(req);
//                if (uploadedKeys.size() > 0) {
//                        for (String b : uploadedKeys) {
//                        	System.out.println("Uploaded " + b);
//                                log.info("Uploaded " + b);
//                        }
//                } else {
//                        log.warning("empty upload");
//                }
                

        		BlobstoreService blobstoreService = BlobstoreServiceFactory
        				.getBlobstoreService();
        		Map<String, List<BlobKey>> blobKeyMap = blobstoreService
        				.getUploads(request);

        		log.info("MapKeys: "+blobKeyMap.keySet());
        		List<BlobKey> blobKeyList = new LinkedList<BlobKey>();
        		for (List<BlobKey> l : blobKeyMap.values()) {
        			log.info("MapV: "+l);
        			for (BlobKey b : l) {
        				blobKeyList.add(b);
        			}
        		}
        		if(blobKeyList.size() > 1) {
        			log.warning("Only one blob should have been uploaded");
        		}
        		BlobKey blobKey = blobKeyList.get(0);
        		String imageURL = BlobstoreUtil.getImageURL(blobKey);
        		response.sendRedirect("/upload?imgURL="+imageURL);
                
                // Upload URLs are one time use only, need to send a new URL to the client.
//                String newBlobstoreUrl = BlobstoreUtil.getUrl();
//                res.setHeader("Content-Type", "text/html"); // Browser will wrap text/plain in <pre> tags
//                res.getWriter().print(newBlobstoreUrl);
//                res.getWriter().flush();
//                res.getWriter().close();
//                log.info("Returning new blobstore URL " + newBlobstoreUrl);
                

        }

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			super.doGet(request, response);
			
			
			String imageUrl = request.getParameter("imgURL");
			log.info("DO GET imgURL:"+imageUrl);
			response.setHeader("Content-Type", "text/html");
			response.getWriter().println("imgurlstart"+imageUrl+"imgurlend");
			response.getWriter().flush();
			response.getWriter().close();
		}

}
