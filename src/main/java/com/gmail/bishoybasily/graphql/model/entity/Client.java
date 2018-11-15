package com.gmail.bishoybasily.graphql.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.bishoybasily.graphql.model.converter.StringSetConverter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bishoy on 11/27/16.
 */
@Entity(name = "clients")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements ClientDetails {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.gmail.bishoybasily.graphql.model.generator.IdGenerator")
    private String id;

    @Column(unique = true)
    private String clientId;

    @JsonIgnore
    private String clientSecret;

    private boolean scoped;
    private boolean secretRequired;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;

    private String photoName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> authorities = new HashSet<>();

    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> scope = new HashSet<>();

    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> authorizedGrantTypes = new HashSet<>();

    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> redirectUris = new HashSet<>();

    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> registeredRedirectUri = new HashSet<>();

    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> resourceIds = new HashSet<>();

    public String getId() {
        return id;
    }

    public Client setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    public Client setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public Client setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    @Override
    public boolean isScoped() {
        return scoped;
    }

    public Client setScoped(boolean scoped) {
        this.scoped = scoped;
        return this;
    }

    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }

    public Client setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
        return this;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public Client setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        return this;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public Client setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        return this;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    public Client setScope(Set<String> scope) {
        this.scope = scope;
        return this;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public Client setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public Client setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public Client setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
        return this;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public Client setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    @JsonIgnore
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    public Client setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

    public String getPhotoName() {
        return photoName;
    }

    public Client setPhotoName(String photoName) {
        this.photoName = photoName;
        return this;
    }

    @JsonIgnore
    public Client addAuthorities(String... auths) {
        authorities.addAll(Arrays.asList(auths));
        return this;
    }

    @JsonIgnore
    public Client removeAuthorities(String... auths) {
        authorities.addAll(Arrays.asList(auths));
        return this;
    }

    @JsonIgnore
    public Client addGrantTypes(String... grnts) {
        authorizedGrantTypes.addAll(Arrays.asList(grnts));
        return this;
    }

    @JsonIgnore
    public Client addScopes(String... scps) {
        scope.addAll(Arrays.asList(scps));
        return this;
    }


}
