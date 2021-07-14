insert into webpage_component_type (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test Web Page Component Type','page_component_type');
insert into webpage (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test Web Page','test_page');
insert into webpage_component (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name", component_type_id, page_id) values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test Web Page Component','test_page_component',1,1);
insert into authority (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test Authority','test_authority');
insert into authority_option (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,active ,visible ,authority_id , component_id ,page_id) values (1,null,CURRENT_DATE,1,CURRENT_DATE,1,true,true,1,1,1);
insert into authority_group (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test Authority Group','test_authority_group');
insert into authoritygroup_authority (authority_group_id,authority_id) values (1,1);
insert into "role" (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1,'Admin Role','ADMIN');
insert into role_authoritygroup (authority_group_id,role_id) values (1,1);
insert into user_component (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by ,dsc ,"name") values (1,null,CURRENT_DATE,1,CURRENT_DATE,1, 'Test User Component','test_user_component');
insert into "user" (id,discontinue_date,created_at,created_by,updated_at,updated_by,active,last_activation_date,last_name,"name","password",username) values (1,null,CURRENT_DATE,1,CURRENT_DATE,1,true,null,'sahintas','can','','crymnc');
insert into user_role (role_id,user_id) values (1,1);
insert into role_component (role_id,component_id) values (1,1);
insert into user_component_content (id,discontinue_date ,created_at ,created_by ,updated_at ,updated_by,"content",component_id,user_id) values (1,null,CURRENT_DATE,1,CURRENT_DATE,1,'44335061152',1,1);
update "user" set "password"='$2a$11$/wYc1H5qs27tSYoAFBQUAuh5s8QOHaJbF44Apv9vg7dWXa93ZATNi' where id = 1;
insert into oauth_client_details
	(client_id, client_secret,resource_ids, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
values
	('testClientId', '$2a$11$n.MTljZDQKskOCLjztGZF.iitM1IPUBuZizV67aTdrG8MVwXkMCoa','api', 'read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);