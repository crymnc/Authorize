package com.anatoliapark.nursinghome.config.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="clientDetails")
@Table(name="oauth_client_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false, updatable = false)
    private String clientId;

    @Column(name="resource_ids")
    private String resourceIds;

    @Column(name="client_secret")
    private String clientSecret;

    @Column(name="scope")
    private String scope;

    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name="web_server_redirect_uri")
    private String webServerRedirectUri;

    @Column(name="authorities")
    private String authorities;

    @Column(name="access_token_validity")
    private Integer accessTokenValidity;

    @Column(name="refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name="additional_information")
    private String additionalInformation;

    @Column(name="autoapprove")
    private String autoApprove;


}

