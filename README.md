# Memcached Module for Testcontainers

## Usage example

This example creates a memcached client connected to the container, then uses it to add a value to the cache and then retrieve it from the cache.

### JUnit 4 example

```java
public class JUnit4Test {

    @Rule
    public MemcachedContainer memcachedContainer = new MemcachedContainer(DockerImageName.parse("memcached"));


    @Test
    void addAndGet() throws IOException {
        final MemcachedClientIF client = memcachedContainer.getClient();
        client.add("key", 0, "value");
        assertThat(client.get("key")).isEqualTo("value");
    }
}
```

### JUnit 5 example

```java
@Testcontainers
class TestMemcachedContainer {

    @Container
    private final MemcachedContainer memcachedContainer = new MemcachedContainer(DockerImageName.parse("memcached"));

    @Test
    void addAndGet() throws IOException {
        final MemcachedClientIF client = memcachedContainer.getClient();
        client.add("key", 0, "value");
        assertThat(client.get("key")).isEqualTo("value");
    }
}
```

## Adding project dependency

Adding this module to your project dependencies:

### Gradle

Add the following dependency to your `build.gradle` file:

```groovy
testCompile "com.btmatthews.testcontainers:memcached-testcontainer:0.0.1"
```

### Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.btmatthews.testcontainers</groupId>
    <artifactId>memcached-testcontainer</artifactId>
    <version>0.0.1</version>
    <scope>test</scope>
</dependency>
```
