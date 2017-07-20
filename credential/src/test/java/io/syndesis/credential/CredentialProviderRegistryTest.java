/**
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.credential;

import org.junit.Test;
import org.springframework.social.connect.ConnectionFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CredentialProviderRegistryTest {

    @Test
    public void shouldAllowProviderRegistration() {
        final CredentialProviderRegistry registry = new CredentialProviderRegistry();

        @SuppressWarnings("unchecked")
        final CredentialProvider<Object, ?> provider = mock(CredentialProvider.class);

        @SuppressWarnings("unchecked")
        final ConnectionFactory<Object> connectionFactory = mock(ConnectionFactory.class);
        when(provider.id()).thenReturn("a-provider");
        when(provider.connectionFactory()).thenReturn(connectionFactory);

        registry.addCredentialProvider(provider);

        assertThat(registry.getConnectionFactory("a-provider")).isEqualTo(connectionFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldComplainAboutUnregisteredProviders() {
        final CredentialProviderRegistry registry = new CredentialProviderRegistry();

        registry.getConnectionFactory("unregistered");
    }
}