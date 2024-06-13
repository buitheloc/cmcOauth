CREATE TABLE public.resource_assign
(
    resource_assign_id serial NOT NULL,
    resource_assign_type character varying(45) NOT NULL,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_resource_assign" PRIMARY KEY (resource_assign_id)
);

ALTER TABLE IF EXISTS public.resource_assign_id
    OWNER to postgres;