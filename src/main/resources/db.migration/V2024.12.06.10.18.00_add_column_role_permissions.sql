CREATE TABLE public.role_permissions
(
    role_permissions_id serial NOT NULL,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_role_permissions_id" PRIMARY KEY (role_permissions_id)
);

ALTER TABLE IF EXISTS public.role_permissions_id
    OWNER to postgres;