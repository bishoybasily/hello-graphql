package com.gmail.bishoybasily.graphql.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.bishoybasily.graphql.model.converter.StringSetConverter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.gmail.bishoybasily.graphql.model.generator.IdGenerator")
    private String id;
    private String name;
    @Column(unique = true)
    private String username;

    private String photoName;
    private Integer timezone;
    private Date birthDate;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String passwordCode;
    @JsonIgnore
    private boolean enabled;
    @JsonIgnore
    private boolean accountNonExpired;
    @JsonIgnore
    private boolean credentialsNonExpired;
    @JsonIgnore
    private boolean accountNonLocked;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Convert(converter = StringSetConverter.class)
    @Column(length = 1024)
    private Set<String> authorities = new HashSet<>();

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    public User setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhotoName() {
        return photoName;
    }

    public User setPhotoName(String photoName) {
        this.photoName = photoName;
        return this;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public User setTimezone(Integer timezone) {
        this.timezone = timezone;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordCode() {
        return passwordCode;
    }

    public User setPasswordCode(String passwordCode) {
        this.passwordCode = passwordCode;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public User setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public User setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public User setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    @JsonIgnore
    public User addAuthorities(String... auths) {
        authorities.addAll(Arrays.asList(auths));
        return this;
    }

    @JsonIgnore
    public User removeAuthorities(String... auths) {
        authorities.addAll(Arrays.asList(auths));
        return this;
    }

    @JsonIgnore
    public User clearPasswordCode() {
        return setPasswordCode(null);
    }

}
