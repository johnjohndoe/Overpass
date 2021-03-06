[![Build Status](https://travis-ci.org/johnjohndoe/Overpass.svg)](https://travis-ci.org/johnjohndoe/Overpass) [![Download](https://api.bintray.com/packages/tbsprs/maven/Overpass/images/download.svg)](https://bintray.com/tbsprs/maven/Overpass/_latestVersion) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/) [![VersionEye](https://www.versioneye.com/user/projects/5658152672d5f3000900bf0c/badge.svg)](https://www.versioneye.com/user/projects/5658152672d5f3000900bf0c)

# Overpass library

A Java library containing a parser and models for the Overpass-Turbo API available here:

* http://overpass-turbo.eu


## Usage

The following example shows how to query post boxes:

```java
Map<String, String> tags = new HashMap<String, String>() {
    {
        put("amenity", "post_box");
    }
};
NodesQuery nodesQuery = new NodesQuery(600, 52.516667, 13.383333, tags, true, 13);
Call<OverpassResponse> streamsResponseCall = streamsService.getOverpassResponse(
	nodesQuery.getFormattedDataQuery());
// Execute streamsResponse call to send a request to the Overpass-Turbo API.
```

### Gradle build

To deploy the library to your local Maven repository run the following task:

```bash
$ ./gradlew publishToMavenLocal
```

Then, to use the library in your project add the following to
your top level `build.gradle`:

```
allprojects {
    repositories {
        jcenter()
        mavenLocal()
    }
}
```

and to your application module `build.gradle`:


```groovy
dependencies {
    compile 'info.metadude.java.library.overpass:overpass-library:${version}'
}
```

## Tests

Run the following command to execute all tests:

```groovy
$ ./gradlew clean test
```

## Which applications are using this library?

* WhereWhat Android Wear app, [sources][wherewhat-sources]


## Author

* [Tobias Preuss][tobias-preuss]

## License

    Copyright 2015 - 2016 Tobias Preuss

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[tobias-preuss]: https://github.com/johnjohndoe
[wherewhat-sources]: https://github.com/ligi/WhereWhat
