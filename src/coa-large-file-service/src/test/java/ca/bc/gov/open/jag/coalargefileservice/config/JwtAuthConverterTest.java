package ca.bc.gov.open.jag.coalargefileservice.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class JwtAuthConverterTest {

	private static final String TEST_RESOURCE = "TEST";

	@Test
	void testNull() {

		JwtAuthConverter converter = new JwtAuthConverter(TEST_RESOURCE);
		assertThrows(NullPointerException.class, () -> converter.convert(null));

	}

	@Test
	void testJwtMissingResourceAccess() {

		Jwt jwt = Mockito.mock(Jwt.class);

		JwtAuthConverter converter = new JwtAuthConverter(TEST_RESOURCE);
		AbstractAuthenticationToken authenticationToken = converter.convert(jwt);
		Collection<GrantedAuthority> authorities = authenticationToken.getAuthorities();
		assertEquals(0, authorities.size());

	}

	@Test
	void testJwtMissingResource() {

		Jwt jwt = Mockito.mock(Jwt.class);
		Map<String, Object> resourceAccess = new HashMap<String, Object>();
		when(jwt.getClaim(JwtAuthConverter.KEYCLOAK_RESOURCE_ATTRIBUTE)).thenReturn(resourceAccess);

		JwtAuthConverter converter = new JwtAuthConverter(TEST_RESOURCE);
		AbstractAuthenticationToken authenticationToken = converter.convert(jwt);
		Collection<GrantedAuthority> authorities = authenticationToken.getAuthorities();
		assertEquals(0, authorities.size());

	}

	@Test
	void testJwtMissingRoles() {

		Jwt jwt = Mockito.mock(Jwt.class);
		
		Map<String, Object> resource = new HashMap<String, Object>();
		
		Map<String, Object> resourceAccess = new HashMap<String, Object>();
		resourceAccess.put("large-file", resource);
		when(jwt.getClaim(JwtAuthConverter.KEYCLOAK_RESOURCE_ATTRIBUTE)).thenReturn(resourceAccess);
		
		JwtAuthConverter converter = new JwtAuthConverter(TEST_RESOURCE);
		AbstractAuthenticationToken authenticationToken = converter.convert(jwt);
		Collection<GrantedAuthority> authorities = authenticationToken.getAuthorities();
		assertEquals(0, authorities.size());

	}


}
