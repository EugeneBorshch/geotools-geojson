geotools-geojson
================

Tool for converting Geotools [FeatureCollection](http://docs.geotools.org/stable/javadocs/org/geotools/feature/FeatureCollection.html)  to [GeoJson](http://geojson.org) format

Usage
---------------------
```java
        //Get some FeatureCollection
        DefaultFeatureCollection features = ...;


        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        GeometryFactory gf = JTSFactoryFinder.getGeometryFactory(null);
        featureBuilder.add(gf.createPoint(new Coordinate(12.5000, 41.8833)));
        featureBuilder.set("name", "Rome");
        featureBuilder.set("country", "Italy");
        features.add(featureBuilder.buildFeature("1"));


        //Convert to geoJson
        String convertedGeojson = new FeatureToGeoJson().convert(simpleFeatures);
```


Example Output
---------------------
```json
{
	"features": [{
		"properties": {
			"id": "",
			"name": "Rome",
			"country": "Italy",
			"geometry": "POINT (12.5 41.8833)"
		},
		"type": "Feature",
		"geometry": {
			"type": "Point",
			"coordinates": [12.5, 41.8833]
		}
	}],
	"type": "FeatureCollection"
}
```

Nice GeoJson visualizer (GeoJsonLint)(http://geojsonlint.com/)
