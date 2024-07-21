SET SCHEMA PUBLIC;

create table vacancy (
    id bigint NOT NULL AUTO_INCREMENT,
    slug VARCHAR_CASESENSITIVE(255),
    company_name VARCHAR_CASESENSITIVE(255),
    title VARCHAR_CASESENSITIVE(255),
    description CHARACTER LARGE OBJECT,
    remote boolean,
    url VARCHAR_CASESENSITIVE(255),
    tags VARCHAR_CASESENSITIVE(50) array,
    job_types VARCHAR_CASESENSITIVE(50) array,
    location VARCHAR_CASESENSITIVE(255),
    created_at timestamp
);