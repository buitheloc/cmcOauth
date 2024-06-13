CREATE TABLE public.users
(
    user_id serial NOT NULL,
    user_name character varying(45) NOT NULL,
    email character varying(45),
    password character varying(45),
    is_active integer,
    description character varying(45),
    remember_token character varying(255) NOT NULL,
    created_date timestamp with time zone,
    created_by character varying,
    last_modified_by character varying,
    last_modified_date timestamp with time zone,
    CONSTRAINT "PK_user_id" PRIMARY KEY (user_id)
);

ALTER TABLE IF EXISTS public.user_info
    OWNER to postgres;