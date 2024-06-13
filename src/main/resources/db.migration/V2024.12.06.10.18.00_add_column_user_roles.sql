CREATE TABLE public.user_roles
(
    user_roles_id serial NOT NULL,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_user_roles" PRIMARY KEY (user_roles_id)
);

ALTER TABLE IF EXISTS public.user_roles
    OWNER to postgres;