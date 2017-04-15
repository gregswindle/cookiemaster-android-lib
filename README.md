# `cookiemaster-android-lib`

> Native Android extraction from `com.cordova.plugins.cookiemaster.CookieMaster` for reuse in native Android as well as Cordova.

[![Build Status](https://travis-ci.org/gregswindle/cookiemaster-android-lib.svg?branch=develop&style=flat-square)](https://travis-ci.org/gregswindle/cookiemaster-android-lib)  [![codecov](https://codecov.io/gh/gregswindle/cookiemaster-android-lib/branch/develop/graph/badge.svg?style=flat-square)](https://codecov.io/gh/gregswindle/cookiemaster-android-lib) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=gregswindle-cookiemaster-android-lib&template=FLAT)](https://sonarqube.com/dashboard/index/gregswindle-cookiemaster-android-lib) [![Dependency Status](https://gemnasium.com/badges/github.com/gregswindle/cookiemaster-android-lib.svg?style=flat-square)](https://gemnasium.com/github.com/gregswindle/cookiemaster-android-lib) [![Technical Debt](https://sonarqube.com/api/badges/measure?key=gregswindle-cookiemaster-android-lib&metric=sqale_debt_ratio&template=FLAT)](https://sonarqube.com/dashboard?id=gregswindle-cookiemaster-android-lib)
---
<!-- TOC depthFrom:2 depthTo:4 withLinks:1 updateOnSave:1 orderedList:0 -->

- [1. Features](#1-features)
	- [1.1. Get a cookie by URL and name](#11-get-a-cookie-by-url-and-name)
	- [1.2. Set a cookie by URL and name](#12-set-a-cookie-by-url-and-name)
	- [1.3. Set a cookie with JSON](#13-set-a-cookie-with-json)
	- [1.4. Serialize an HttpCookie to JSON](#14-serialize-an-httpcookie-to-json)
	- [1.5. Clear all cookies](#15-clear-all-cookies)
- [2. Setup](#2-setup)
	- [2.1. Maven](#21-maven)
	- [2.2. Gradle](#22-gradle)
- [3. Snapshots](#3-snapshots)
- [4. Recommended prerequisites](#4-recommended-prerequisites)
	- [4.1. `Homebrew`](#41-homebrewhttpsbrewsh)
	- [4.2. `jenv`](#42-jenvhttpwwwjenvbe)
	- [4.3. `java`](#43-java)
	- [4.4. `gradle`](#44-gradlehttpsgradleorginstallwith-homebrew)
- [5. Reports :chart_with_upwards_trend:](#5-reports-chartwithupwardstrend)
	- [5.1. Code quality and test reports](#51-code-quality-and-test-reports)
	- [5.2. Project dependency updates](#52-project-dependency-updates)
	- [5.3. Project dependencies tree in a Terminal](#53-project-dependencies-tree-in-a-terminal)
	- [5.4. Dependency reports for Web browsers](#54-dependency-reports-for-web-browsers)
- [6. Dependency management](#6-dependency-management)
- [7. Deployment](#7-deployment)
<!-- /TOC -->

## 1. Features

### 1.1. Get a cookie by URL and name

```java
String url = "https://example.com/cookies";
String cookieName = "foo";
HttpCookie cookie = CookieMaster.getCookieValue(url, cookieName);
```

### 1.2. Set a cookie by URL and name

```java
String url = "https://example.com/cookies";
String cookieName = "foo";
String cookieValue = "bar";
CookieMaster.getCookieValue(url, cookieName, cookieValue);
```

### 1.3. Set a cookie with JSON

```java
// json is a serialized HttpCookie
HttpCookie cookie = CookieMaster.cookieFromJson(json);
```

### 1.4. Serialize an HttpCookie to JSON

```java
// cookie is an HttpCookie
String jsonCookie = CookieMaster.cookieToJson(cookie);
```

### 1.5. Clear all cookies

```java
CookieMaster.clear();
```

## 2. Setup

Releases ~~are~~ _will be_ published to [bintray jcenter](https://bintray.com/gregswindle/cookiemaster-android-lib/cookiemaster-android-lib/) and
[maven central](https://maven-badges.herokuapp.com/maven-central/com.verizon.api/cookiemaster-android-lib).

[![License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](http://www.opensource.org/licenses/MIT)  [![JCenter](https://img.shields.io/bintray/v/gregswindle/cookiemaster-android-lib/cookiemaster-android-lib.svg?label=jcenter)](https://bintray.com/gregswindle/cookiemaster-android-lib/cookiemaster-android-lib/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/com.verizon.api/cookiemaster-android-lib.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.verizon.api/cookiemaster-android-lib)


### 2.1. Maven

```xml
<dependency>
  <groupId>com.verizon.api</groupId>
  <artifactId>cookiemaster-android-lib</artifactId>
  <version>0.1.0</version>
</dependency>
```

### 2.2. Gradle

Add `com.verizon.api:cookiemaster-android-lib` to your `build.gradle`'s `dependencies`:

```gradle
compile 'com.verizon.api:cookiemaster-android-lib:0.1.0'
```

## 3. Snapshots

You can use snapshot versions through [JitPack](https://jitpack.io).

3.1. Go to [JitPack project page](https://jitpack.io/#gregswindle/cookiemaster-android-lib).

3.2. Select `Commits` section and click `Get it` on commit you want to use (top one - the most recent).

3.3. Follow displayed instructions: add repository and change dependency (NOTE: due to JitPack convention artifact group will be different).

## 4. Recommended prerequisites

The following dependencies are recommended for consistent build, test, and deploy tasks:

### 4.1. [`Homebrew`](https://brew.sh/)

Install [`Homebrew`](https://brew.sh/):
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

### 4.2. [`jenv`](http://www.jenv.be/)

Install [`jenv`](http://www.jenv.be/):

```
$ brew install jenv
```

### 4.3. `java`

Install [`Java`](http://davidcai.github.io/blog/posts/install-multiple-jdk-on-mac/) from a Terminal:

```
$ brew cask install java
```

> #### :white_check_mark: Install several versions of `Java`
>
> If you want to test this library on several versions of `Java`, the article [_Install Multiple Java Versions on Mac_](http://davidcai.github.io/blog/posts/install-multiple-jdk-on-mac/) provides clear yet detailed instructions.

### 4.4. [`gradle`](https://gradle.org/install#with-homebrew)

Install [`gradle`](https://gradle.org/install#with-homebrew) for automated build and dependency management:

```
$ brew install gradle
```

## 5. Reports :chart_with_upwards_trend:

> ### :white_check_mark: Generate a project report
>
> Generate project reports for all dependencies, properties, and tasks in your `<project-root>/build/reports/project` directory:
> ```
> $ ./gradlew projectReport
> ```

### 5.1. Code quality and test reports

Use the following command to run code quality plugins and tests. If quality checks were activated (asked during generation) do check before pushing to avoid build failures on travis. Moreover, it's easy to always keep everything clean instead of doing it before release.

```
$ ./gradlew check
```

### 5.2. Project dependency updates

Checks whether your project is using the latest available versions in its dependencies. If not, you'll see which upgrades are available.

```
$ ./gradlew dependencyUpdates
```

### 5.3. Project dependencies tree in a Terminal

Print the product's dependency tree in your console.

```
$ ./gradlew dependencies
```

### 5.4. Dependency reports for Web browsers

Generate a _Dependency Report_ in `HTML` and launch it in your default browser.

> ##### :white_check_mark: Analyze dependency conflicts
>
> To analyze conflicts, click on dependency name to activate a [`dependencyInsight`](http://www.gradle.org/docs/current/groovydoc/org/gradle/api/tasks/diagnostics/DependencyInsightReportTask.html) pop-up in the _Dependency Report_.

```
$ ./gradlew showDependenciesTree
```

## 6. Dependency management

:link: Install libraries to your local `maven` repository. Useful for referencing by other projects (for testing without releasing library).

```
$ ./gradlew install
```

## 7. Deployment

:globe_with_meridians: Publish the library to [jFrog Bintray](https://bintray.com/) (and therefore the world).

> #####  :warning: Read the [_Release process_](https://github.com/xvik/generator-lib-java#release-process) first!
>
> Before publishing the library, [read the section _Release process_](https://github.com/xvik/generator-lib-java#release-process) in the `generator-lib-java` README.md.

```
$ ./gradlew release
```

---
[![java lib generator](http://img.shields.io/badge/Powered%20by-%20Java%20lib%20generator-green.svg?style=flat-square)](https://github.com/xvik/generator-lib-java)
