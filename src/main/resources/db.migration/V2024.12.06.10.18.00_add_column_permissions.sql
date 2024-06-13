CREATE TABLE public.permissions
(
    permission_id serial NOT NULL,
    permission_name character varying(45) NOT NULL,
    description character varying(45),
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "permissions_id" PRIMARY KEY (permission_id)
);

ALTER TABLE IF EXISTS public.permissions
    OWNER to postgres;