package com.example.shop.model.roles;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;



public enum Role {
	 BUYER(Set.of(Permission.READ)),
	 SELLER(Set.of(Permission.READ, Permission.WRITE));

	    private final Set<Permission> permissions;

	    Role(Set<Permission> permissions) {
	        this.permissions = permissions;
	    }

	    public Set<Permission> getPermissions() {
	        return permissions;
	    }

	    public Set<SimpleGrantedAuthority> getAuthorities() {
	        return getPermissions().stream()
	                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	                .collect(Collectors.toSet());
	    }

}
