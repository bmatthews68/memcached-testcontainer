package com.btmatthews.testcontainers.memcached;

import net.spy.memcached.MemcachedClientIF;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class TestMemcachedContainer {

    @Container
    private static final MemcachedContainer memcachedContainer = new MemcachedContainer(DockerImageName.parse("memcached"));

    @Test
    void client() throws IOException {
        final MemcachedClientIF client = memcachedContainer.getClient();
        client.add("key", 0, "value");
        assertThat(client.get("key")).isEqualTo("value");
    }
}
