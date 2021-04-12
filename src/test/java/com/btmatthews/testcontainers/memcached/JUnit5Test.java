package com.btmatthews.testcontainers.memcached;

import net.spy.memcached.MemcachedClientIF;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

// tag::example[]
@Testcontainers
class JUnit5Test {

    @Container
    private final MemcachedContainer memcachedContainer = new MemcachedContainer(DockerImageName.parse("memcached"));

    @Test
    void addAndGet() throws IOException {
        final MemcachedClientIF client = memcachedContainer.getClient();
        client.add("key", 0, "value");
        assertThat(client.get("key")).isEqualTo("value");
    }
}
// end::example[]
