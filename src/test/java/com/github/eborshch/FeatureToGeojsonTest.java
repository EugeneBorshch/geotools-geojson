package com.github.eborshch;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geotools.data.DataUtilities;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
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
    public void testTransformation() throws SchemaException {
        DefaultFeatureCollection simpleFeatures = prepareTestData();

        String to = new FeatureToGeoJson().to(simpleFeatures);
        System.out.println(to);

    }

    private DefaultFeatureCollection prepareTestData() throws SchemaException {
        SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "geometry:Point:srid=4326," + // <- the geometry attribute: Point type
                        "ID:Integer," +
                        "name:String," +
                        "country:String"
        );


        DefaultFeatureCollection features = new DefaultFeatureCollection(null, TYPE);


        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        GeometryFactory gf = JTSFactoryFinder.getGeometryFactory(null);
        featureBuilder.add(gf.createPoint(new Coordinate(12.5000, 41.8833)));
        featureBuilder.add("Rome");
        featureBuilder.add("Italy");
        features.add(featureBuilder.buildFeature("1"));

        // New York
        featureBuilder.add(gf.createPoint(new Coordinate(-74.0000, 40.7000)));
        featureBuilder.add("New York");
        featureBuilder.add("USA");
        features.add(featureBuilder.buildFeature("2"));

        // Paris
        featureBuilder.add(gf.createPoint(new Coordinate(2.3333, 48.8667)));
        featureBuilder.add("Paris");
        featureBuilder.add("France");
        features.add(featureBuilder.buildFeature("3"));

        // London
        featureBuilder.add(gf.createPoint(new Coordinate(-0.0833, 51.5000)));
        featureBuilder.add("London");
        featureBuilder.add("England");
        features.add(featureBuilder.buildFeature("4"));

        return features;

    }
}
