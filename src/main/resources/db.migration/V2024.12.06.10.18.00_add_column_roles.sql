CREATE TABLE public.roles
(
    role_id serial NOT NULL,
    role_name character varying(45) NOT NULL,
    slug character varying(45),
    description character varying(45),
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_role_id" PRIMARY KEY (role_id)
);

ALTER TABLE IF EXISTS public.cmc_roles
    OWNER to postgres;