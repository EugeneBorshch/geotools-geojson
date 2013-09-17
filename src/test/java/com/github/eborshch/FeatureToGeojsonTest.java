package com.github.eborshch;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Test for FeatureToGeojson
 * <p/>
 * User: Eugene Borshch
 * Date: 9/17/13
 */
public class FeatureToGeojsonTest {

    @Test
    public void testConversion() throws SchemaException {
        DefaultFeatureCollection simpleFeatures = prepareTestData();

        String convertedGeojson = new FeatureToGeoJson().convert(simpleFeatures);
        System.out.println(convertedGeojson);

        Assert.assertNotNull(JSONValue.parse(convertedGeojson));
    }

    private DefaultFeatureCollection prepareTestData() throws SchemaException {
        SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "geometry:Point:srid=4326," +
                        "id:String," +
                        "name:String," +
                        "country:String"
        );


        DefaultFeatureCollection features = new DefaultFeatureCollection(null, TYPE);


        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        GeometryFactory gf = JTSFactoryFinder.getGeometryFactory(null);
        featureBuilder.add(gf.createPoint(new Coordinate(12.5000, 41.8833)));
        featureBuilder.set("name", "Rome");
        featureBuilder.set("country", "Italy");
        features.add(featureBuilder.buildFeature("1"));

        // New York
        featureBuilder.add(gf.createPoint(new Coordinate(-74.0000, 40.7000)));
        featureBuilder.set("name", "New York");
        featureBuilder.set("country", "USA");
        features.add(featureBuilder.buildFeature("2"));

        return features;

    }
}
