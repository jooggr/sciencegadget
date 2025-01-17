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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.sciencegadgets.client.entities.QuantityKind;
import com.sciencegadgets.client.entities.Unit;

public class OwlMiner {

	TreeMap<String, String> map = new TreeMap<String, String>();
	HashSet<String> set = new HashSet<String>();

	/**
	 * ObjectifyService.ofy().load().type(QuantityKind.class).id(qKind).now();
	 * 
	 * ObjectifyService.ofy().load().type(Unit
	 * .class).ancestor(Key.create(QuantityKind.class, qKind)).list();
	 */
	public void recreateUnitsFromOwl() throws IllegalArgumentException {

		// Delete all
		List<QuantityKind> qKinds = ObjectifyService.ofy().load()
				.type(QuantityKind.class).list();
		ArrayList<Key<Unit>> unitKeys = new ArrayList<Key<Unit>>();
		ArrayList<String> qKindsNames = new ArrayList<String>(qKinds.size());

		for (QuantityKind q : qKinds) {
			qKindsNames.add(q.getId());

			Key<QuantityKind> qkey = Key.create(QuantityKind.class, q.getId());

			List<Unit> units = ObjectifyService.ofy().load().type(Unit.class)
					.ancestor(qkey).list();

			for (Unit u : units) {
				Key<Unit> ukey = Key.create(qkey, Unit.class, u.getName().toString());
				unitKeys.add(ukey);
			}
		}
		//TODO
//		ObjectifyService.ofy().delete().keys(unitKeys).now();
//		ObjectifyService.ofy().delete().type(QuantityKind.class)
//				.ids(qKindsNames).now();
		
		// ///////////////////////////////
		// Mine owl for QuatityKinds
		// ///////////////////////////////
		Document doc = getDoc("Data/qudt-1.0/dimension.owl");
		NodeList dimensionNodes = doc.getElementsByTagName("qudt:Dimension");

		// aa.add("Inductance");
		// aa.add("ElectricCurrentPerUnitLength");
		// aa.add("EnergyPerElectricCharge");
		// aa.add("Capacitance");
		// aa.add("MagneticField");
		// aa.add("ElectricConductivity");
		// aa.add("ElectricChargePerArea");
		// aa.add("ElectricField");
		// aa.add("Permeability");
		// aa.add("ElectricCharge");
		// aa.add("Resistance");
		// aa.add("ElectricCurrentDensity");
		// aa.add("ElectricCurrent");
		// aa.add("Permittivity");
		// aa.add("MagneticFlux");

		for (int i = 0; i < dimensionNodes.getLength(); i++) {
			Element dimension = (Element) dimensionNodes.item(i);
			NodeList baseVectors = dimension
					.getElementsByTagName("qudt:dimensionVector");
			String U = "0", L = "0", M = "0", T = "0", I = "0", Θ = "0", N = "0", J = "0";
			for (int j = 0; j < baseVectors.getLength(); j++) {
				Element base = (Element) baseVectors.item(j);
				String baseAttribute = base.getAttribute("rdf:resource");
				if (baseAttribute.equals("")) {
					baseAttribute = ((Element) base.getElementsByTagName(
							"qudt:DimensionVector").item(0)).getAttribute(
							"rdf:about").replace(
							"http://data.nasa.gov/qudt/owl/dimension#Vector_",
							"");
				} else {
					baseAttribute = baseAttribute.replaceFirst(
							"http://data.nasa.gov/qudt/owl/dimension#Vector_",
							"");
				}
				String baseType = "" + baseAttribute.charAt(0);
				String baseValue = baseAttribute.replace(baseType, "");

				// dimension notation 0,0,0,0,0,0,0,0
				if (baseType.equals("U")) {
					U = baseValue;
				} else if (baseType.equals("L")) {
					L = baseValue;
				} else if (baseType.equals("M")) {
					M = baseValue;
				} else if (baseType.equals("T")) {
					T = baseValue;
				} else if (baseType.equals("I")) {
					I = baseValue;
				} else if (baseType.equals("Θ")) {
					Θ = baseValue;
				} else if (baseType.equals("N")) {
					N = baseValue;
				} else if (baseType.equals("J")) {
					J = baseValue;
				}
			}
			String dim = U + "," + L + "," + M + "," + T + "," + I + "," + Θ
					+ "," + N + "," + J;

			String quantity = ((Element) dimension.getElementsByTagName(
					"qudt:referenceQuantity").item(0)).getAttribute(
					"rdf:resource").replace(
					"http://data.nasa.gov/qudt/owl/quantity#", "");

			map.put(quantity, dim);

		}
		map.put("Prefix", "1,0,0,0,0,0,0,0");
		map.put("PrefixBinary", "1,0,0,0,0,0,0,0");
		map.put("Angle", "1,0,0,0,0,0,0,0");
		map.put("SolidAngle", "2,0,0,0,0,0,0,0");
		map.remove("AreaPerTime");
		map.put("KinematicViscosity", "0,2,0,-1,0,0,0,0");
		map.remove("EnergyPerTemperature");
		map.put("HeatCapacity", "0,2,1,-2,0,-1,0,0");
		map.put("Radioactivity", "0,0,0,-1,0,0,0,0");
		map.put("EnergyPerArea", "0,0,1,-2,0,0,0,0");
		map.put("InformationEntropy", "1,0,0,0,0,0,0,0");
		map.remove("ElectricCurrentPerUnitLength");
		map.put("AuxillaryMagneticField", "0,-1,0,0,1,0,0,0");
		map.remove("EnergyAndWork");
		map.put("Energy", "0,2,1,-2,0,0,0,0");
		map.remove("LinearAcceleration");
		map.put("Acceleration", "0,1,0,-2,0,0,0,0");
		map.remove("LinearVelocity");
		map.put("Velocity", "0,1,0,-1,0,0,0,0");
		map.remove("ThermodynamicTemperature");
		map.put("Temperature", "0,0,0,0,0,1,0,0");
		

		// ////////////////////////////////
		// Mine owl for Units
		// ////////////////////////////////
		Document unitDoc = getDoc("Data/qudt-1.0/unit.owl");
		NodeList eqNodes = unitDoc.getElementsByTagName("*");

		for (int i = 0; i < eqNodes.getLength(); i++) {
			Element node = (Element) eqNodes.item(i);
			if (!node.getNodeName().endsWith("Unit")
					|| node.getNodeName().startsWith("qudt:system")
					|| node.getNodeName().startsWith("qudt:Currency")) {
				continue;
			}

			String qKind = null;

			String label = extractProperty(node, "rdfs:label");
			if (label.equals("Millimeter of Mercury")) {
				// Not actually prefixed
			} else if (label.equals("Kilogram")) {
				// Don't exclude, SI base unit
			} else if (label.equals("Yotta") || label.equals("Zetta")
					|| label.equals("Exa") || label.equals("Peta")
					|| label.equals("Tera") || label.equals("Giga")
					|| label.equals("Mega") || label.equals("Kilo")
					|| label.equals("Hecto") || label.equals("Deca")
					|| label.equals("Deci") || label.equals("Centi")
					|| label.equals("Milli") || label.equals("Micro")
					|| label.equals("Nano") || label.equals("Pico")
					|| label.equals("Femto") || label.equals("Atto")
					|| label.equals("Zepto") || label.equals("Yocto")) {
				qKind = "Prefix";
			} else if (// binary prefix

			label.equals("Kibi") || label.equals("Mebi")
					|| label.equals("Gibi") || label.equals("Tebi")
					|| label.equals("Pebi") || label.equals("Exbi")
					|| label.equals("Zebi") || label.equals("Yobi")) {
				qKind = "PrefixBinary";
			} else if (// remove units starting with prefixes. Prefix or base
						// only
			label.startsWith("Yotta") || label.startsWith("Zetta")
					|| label.startsWith("Exa") || label.startsWith("Peta")
					|| label.startsWith("Tera") || label.startsWith("Giga")
					|| label.startsWith("Mega") || label.startsWith("Kilo")
					|| label.startsWith("Hecto") || label.startsWith("Deca")
					|| label.startsWith("Deci") || label.startsWith("Centi")
					|| label.startsWith("Milli") || label.startsWith("Micro")
					|| label.startsWith("Nano") || label.startsWith("Pico")
					|| label.startsWith("Femto")
					|| label.startsWith("Atto")
					|| label.startsWith("Zepto")
					|| label.startsWith("Yocto")
					// binary prefix
					|| label.startsWith("Kibi") || label.startsWith("Mebi")
					|| label.startsWith("Gibi") || label.startsWith("Tebi")
					|| label.startsWith("Pebi") || label.startsWith("Exbi")
					|| label.startsWith("Zebi") || label.startsWith("Yobi")) {
				continue;
			}

			String offset = extractProperty(node, "qudt:conversionOffset");
			if (offset == null || offset.equals("0.0") || offset.equals("0")) {
				offset = "0";
			} else if (label.equals("Degree Celsius")
					|| label.equals("Degree Fahrenheit")) {
				continue;
			}

			String multiplier = extractProperty(node,
					"qudt:conversionMultiplier");
			if (multiplier == null) {
				continue;
			} else {
				multiplier = multiplier.replace("1.0e0", "1")
						.replace("1.0E0", "1").replace("e", "E");
			}

			if (qKind == null) {
				NodeList a = node.getElementsByTagName("qudt:quantityKind");
				if (a.getLength() > 0) {
					qKind = ((Element) a.item(0)).getAttribute("rdf:resource")
							.replaceFirst(
									"http://data.nasa.gov/qudt/owl/quantity#",
									"");
					//Rename quantity Kinds
					if (qKind.equals("LiquidVolume")
							|| qKind.equals("DryVolume")) {
						qKind = "Volume";
					} else if (qKind.equals("PlaneAngle")) {
						qKind = "Angle";
					} else if (qKind.equals("HeatFlowRate")) {
						qKind = "Power";
					} else if (qKind.equals("MolecularMass")) {
						qKind = "Mass";
					} else if (qKind.equals("AreaPerTime")) {
						qKind = "HeatCapacity";
					} else if (qKind.equals("EnergyPerTemperature")) {
						qKind = "KinematicViscosity";
					} else if (qKind.equals("ThermalDiffusivity")) {
						qKind = "AreaThermalExpansion";
					} else if (qKind.equals("Activity")) {
						qKind = "Radioactivity";
					} else if (qKind.equals("Exposure")) {
						qKind = "ElectricChargePerMass";
					} else if (qKind.equals("AbsorbedDose")
							|| qKind.equals("DoseEquivalent")) {
						qKind = "SpecificEnergy";
					} else if (qKind.equals("ThermalEnergy")) {
						qKind = "Energy";
						
					} else if (qKind.equals("EnergyAndWork")) {
						qKind = "Energy";
					} else if (qKind.equals("ThermodynamicTemperature")) {
						qKind = "Temperature";
					} else if (qKind.equals("LinearAcceleration")) {
						qKind = "Acceleration";
					} else if (qKind.equals("LinearVelocity")) {
						qKind = "Velocity";
						
						//No need for these quantity kinds
					} else if (qKind.equals("MassAmountOfSubstance")) {
						continue;
					} else if (qKind.equals("ForcePerElectricCharge")) {
						continue;
					} else if (qKind.equals("MassPerLength")) {
						continue;
					} else if (qKind.equals("Permeability")) {
						continue;
					} else if (qKind.equals("Permittivity")) {
						continue;
					}
				} else {
					continue;
				}
			}

			String systemUnit = node.getParentNode().getParentNode()
					.getNodeName();
			if (systemUnit.equals("qudt:SystemOfUnits")) {
				systemUnit = ((Element) node.getParentNode().getParentNode())
						.getAttribute("rdf:about")
						.replace(
								"http://data.nasa.gov/qudt/owl/unit#SystemOfUnits_",
								"");
			}

			String symbol = extractProperty(node, "qudt:symbol");
			String symbolFixed = symbol;
			if (symbol == null) {
				continue;
			} else if (qKind.equals("Prefix")) {
				symbolFixed = label;
			} else if (qKind.equals("PrefixBinary")) {
				symbolFixed = label;

				// Close enough to international
			} else if (label.equals("US Survey Foot")) {
				continue;
			} else if (label.equals("Mile US Statute")) {
				continue;

				// Duplicate
			} else if ((label.equals("Biot") || label.equals("Metric Ton") || label
					.equals("Gon"))
					&& systemUnit.equals("qudt:NotUsedWithSIUnit")) {
				continue;

				// No space
			} else if (symbol.equals("therm (US)")) {
				symbolFixed = "therm[US]";
			} else if (symbol.equals("therm (EC)")) {
				symbolFixed = "therm[EC]";

				// Incorrect
				// } else if (symbol.equals("(K^2)m/W")) {
				// symbolFixed = "K*m^2*W^-1";
				// } else if (symbol.equals("ft-lbf(ft^2-s)")) {
				// symbolFixed = "ft^-1*lbf*s^-1)";

				// Confusing
			} else if (label.equals("Ounce Mass")) {
				symbolFixed = "oz";
			} else if (label.equals("Pound Mass")) {
				symbolFixed = "lb";
			} else if (label.equals("Ton - Long")) {
				symbolFixed = "ton[UK]";
			} else if (label.equals("Imperial Pint")) {
				symbolFixed = "pt[UK])";
			} else if (label.equals("Imperial Gallon")) {
				symbolFixed = "gal[UK]";
			} else if (label.equals("Imperial Ounce")) {
				symbolFixed = "fl.oz.[UK]";
			} else if (label.equals("Ton - Short")) {
				symbolFixed = "ton[US]";
			} else if (label.equals("US Liquid Pint")) {
				symbolFixed = "pt[US]";
			} else if (label.equals("US Gallon")) {
				symbolFixed = "gal[US]";
			} else if (label.equals("US Liquid Ounce")) {
				symbolFixed = "fl.oz.[US]";
			} else if (label.equals("Ounce Troy")) {
				symbolFixed = "oz[Troy]";
			} else if (label.equals("Pound Troy")) {
				symbolFixed = "lb[Troy]";

				// No underscore (_)
			} else if (symbolFixed.equals("dry_qt")) {
				symbolFixed = "qt[dry]";
			} else if (symbolFixed.equals("dry_gal")) {
				symbolFixed = "gal[dry]";
			} else if (symbolFixed.equals("dry_pt")) {
				symbolFixed = "pt[dry]";
			} else if (symbolFixed.equals("E_h")) {
				symbolFixed = "Eh";
			} else if (symbolFixed.equals("Θ_P")) {
				symbolFixed = "Θp";
			} else if (symbolFixed.equals("l_P")) {
				symbolFixed = "lp";
			} else if (symbolFixed.equals("m_P")) {
				symbolFixed = "mp";
			} else if (symbolFixed.equals("t_P")) {
				symbolFixed = "tp";
			} else if (symbolFixed.equals("Q_p")) {
				symbolFixed = "Qp";

				// Conflicting
				// } else if (label.equals("Faraday")) {
				// symbolFixed = "Faraday";// Farad=F
				// } else if (label.equals("Cord")) {
				// symbolFixed = "Cord";// Colomb=C
				// } else if (label.equals("Diopter")) {
				// symbolFixed = "dpt";// Debye=D
				// } else if (label.equals("Rad")) {
				// symbolFixed = "rad(dose)";// radian=rad
				// } else if (label.equals("Gauss")) {
				// symbolFixed = "Gs";// Gravity=G
				// } else if (label.equals("Bit")) {
				// symbolFixed = "bit";// Barn=b
				// } else if (label.equals("Year Sidereal")) {
				// symbolFixed = "yr(side)";// year 365=yr
				// } else if (label.equals("Lambert")) {
				// symbolFixed = "la";// Liter=L

				// Standardize derived units
				// } else {
				// symbolFixed = symbolFixed.replace("^-", "expNeg")
				// .replace("-", "*").replace(" / ", "/")
				// .replace(" ", "*").replace("(", "").replace(")", "");
				// symbolFixed = symbolFixed.replace("expNeg", "^-");
				//
				// String[] fractionParts = symbolFixed.split("/");
				//
				// symbolFixed = fractionParts[0];
				// if (fractionParts.length > 1) {
				// String[] denominator = fractionParts[1].split("\\*");
				// for (String den : denominator) {
				// if (den.contains("^")) {
				// den = den.replace("^", "^-");
				// } else {
				// den = den + "^-1";
				// }
				// symbolFixed = symbolFixed + "*" + den;
				// }
				//
				// String[] symbolParts = symbolFixed.split("\\*");
				// Arrays.sort(symbolParts);
				// symbolFixed = "";
				// for (String part : symbolParts) {
				// if (symbolFixed.equals("")) {
				// symbolFixed = part;
				// } else {
				// symbolFixed = symbolFixed + "*" + part;
				// }
				// }
				// }
			} else if (symbolFixed.contains("^") || symbolFixed.contains(" ")
					|| symbolFixed.contains("-") || symbolFixed.contains("/")
					|| symbolFixed.contains("(") || symbolFixed.contains(")")) {
				continue;
			}

			// System.out.println(qKind + "_" + symbolFixed + "," + label + ","
			// + multiplier);

			Key<QuantityKind> qKindKey = Key.create(QuantityKind.class, qKind);
//			Unit unit = new Unit(qKind + "_" + symbolFixed, qKindKey, label,
//					extractProperty(node, "qudt:description"), multiplier);
			//TODO
//			ObjectifyService.ofy().save().entity(unit);
			
			System.out.println(qKind+"_"+i+ "(\""+qKind + "_" + symbolFixed+"\", \""+qKind+"\", \""+label+"\", \""+extractProperty(node, "qudt:description")+"\", \""+multiplier+"\"),");

			// mark quantity kind as used as a parent
			String oldDim = map.get(qKind);
			
			map.put(qKind, oldDim.replace(",", "_"));
		}

		for (String quantity : map.keySet()) {
			// If used as parent, save quantity kind
			if (map.get(quantity).contains("_")) {
				//TODO
//				ObjectifyService
//						.ofy()
//						.save()
//						.entity(new QuantityKind(quantity, map.get(quantity)
//								.replace("_", ","))).now();

//				 System.out.println(quantity+"( \""+map.get(quantity)
//							.replace("_", ",")+"\"),");
			}
		}
	}

	private String extractProperty(Element node, String property) {
		// extractProperty(node, "rdfs:label")
		// extractProperty(node, "qudt:symbol")
		// extractProperty(node, "qudt:code")
		// extractProperty(node, "qudt:description")
		// extractProperty(node, "qudt:conversionOffset")
		// extractProperty(node, "qudt:conversionMultiplier")
		NodeList a = node.getElementsByTagName(property);
		if (a.getLength() > 0) {
			return ((Element) a.item(0)).getTextContent();
		} else {
			return null;
		}
	}

	private Document getDoc(String path) {
		Document doc = null;

		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
