PGDMP      5                |            booking    16.3    16.3 !    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    booking    DATABASE     y   CREATE DATABASE booking WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Peru.1252';
    DROP DATABASE booking;
                postgres    false            �            1259    16399    booking    TABLE     h  CREATE TABLE public.booking (
    id character varying(255) NOT NULL,
    age character varying(255),
    discount_code character varying(255),
    end_date date,
    house_id character varying(255),
    lastname character varying(255),
    name character varying(255),
    phone_number character varying(255),
    start_date timestamp(6) without time zone
);
    DROP TABLE public.booking;
       public         heap    postgres    false            �           0    0    TABLE booking    ACL     s   REVOKE ALL ON TABLE public.booking FROM postgres;
GRANT ALL ON TABLE public.booking TO postgres WITH GRANT OPTION;
          public          postgres    false    215            �            1259    16407    rol    TABLE     e   CREATE TABLE public.rol (
    id integer NOT NULL,
    rol_nombre character varying(255) NOT NULL
);
    DROP TABLE public.rol;
       public         heap    postgres    false            �           0    0 	   TABLE rol    ACL     k   REVOKE ALL ON TABLE public.rol FROM postgres;
GRANT ALL ON TABLE public.rol TO postgres WITH GRANT OPTION;
          public          postgres    false    217            �            1259    16406 
   rol_id_seq    SEQUENCE     �   CREATE SEQUENCE public.rol_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.rol_id_seq;
       public          postgres    false    217            �           0    0 
   rol_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.rol_id_seq OWNED BY public.rol.id;
          public          postgres    false    216            �           0    0    SEQUENCE rol_id_seq    ACL        REVOKE ALL ON SEQUENCE public.rol_id_seq FROM postgres;
GRANT ALL ON SEQUENCE public.rol_id_seq TO postgres WITH GRANT OPTION;
          public          postgres    false    216            �            1259    16414    usuario    TABLE     �   CREATE TABLE public.usuario (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    nombre_usuario character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �           0    0    TABLE usuario    ACL     s   REVOKE ALL ON TABLE public.usuario FROM postgres;
GRANT ALL ON TABLE public.usuario TO postgres WITH GRANT OPTION;
          public          postgres    false    219            �            1259    16413    usuario_id_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.usuario_id_seq;
       public          postgres    false    219            �           0    0    usuario_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;
          public          postgres    false    218            �           0    0    SEQUENCE usuario_id_seq    ACL     �   REVOKE ALL ON SEQUENCE public.usuario_id_seq FROM postgres;
GRANT ALL ON SEQUENCE public.usuario_id_seq TO postgres WITH GRANT OPTION;
          public          postgres    false    218            �            1259    16422    usuario_rol    TABLE     b   CREATE TABLE public.usuario_rol (
    usuario_id integer NOT NULL,
    rol_id integer NOT NULL
);
    DROP TABLE public.usuario_rol;
       public         heap    postgres    false            �           0    0    TABLE usuario_rol    ACL     {   REVOKE ALL ON TABLE public.usuario_rol FROM postgres;
GRANT ALL ON TABLE public.usuario_rol TO postgres WITH GRANT OPTION;
          public          postgres    false    220            '           2604    16410    rol id    DEFAULT     `   ALTER TABLE ONLY public.rol ALTER COLUMN id SET DEFAULT nextval('public.rol_id_seq'::regclass);
 5   ALTER TABLE public.rol ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            (           2604    16417 
   usuario id    DEFAULT     h   ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);
 9   ALTER TABLE public.usuario ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �          0    16399    booking 
   TABLE DATA           w   COPY public.booking (id, age, discount_code, end_date, house_id, lastname, name, phone_number, start_date) FROM stdin;
    public          postgres    false    215   �#       �          0    16407    rol 
   TABLE DATA           -   COPY public.rol (id, rol_nombre) FROM stdin;
    public          postgres    false    217   $       �          0    16414    usuario 
   TABLE DATA           N   COPY public.usuario (id, email, nombre, nombre_usuario, password) FROM stdin;
    public          postgres    false    219   @$       �          0    16422    usuario_rol 
   TABLE DATA           9   COPY public.usuario_rol (usuario_id, rol_id) FROM stdin;
    public          postgres    false    220   �$       �           0    0 
   rol_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.rol_id_seq', 3, true);
          public          postgres    false    216            �           0    0    usuario_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.usuario_id_seq', 1, true);
          public          postgres    false    218            *           2606    16405    booking booking_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pkey;
       public            postgres    false    215            ,           2606    16412    rol rol_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public            postgres    false    217            .           2606    16428 $   usuario uk_puhr3k3l7bj71hb7hk7ktpxn0 
   CONSTRAINT     i   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_puhr3k3l7bj71hb7hk7ktpxn0 UNIQUE (nombre_usuario);
 N   ALTER TABLE ONLY public.usuario DROP CONSTRAINT uk_puhr3k3l7bj71hb7hk7ktpxn0;
       public            postgres    false    219            0           2606    16421    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    219            2           2606    16426    usuario_rol usuario_rol_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (usuario_id, rol_id);
 F   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT usuario_rol_pkey;
       public            postgres    false    220    220            3           2606    16429 '   usuario_rol fk610kvhkwcqk2pxeewur4l7bd1    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT fk610kvhkwcqk2pxeewur4l7bd1 FOREIGN KEY (rol_id) REFERENCES public.rol(id);
 Q   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT fk610kvhkwcqk2pxeewur4l7bd1;
       public          postgres    false    217    220    4652            4           2606    16434 '   usuario_rol fkbyfgloj439r9wr9smrms9u33r    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT fkbyfgloj439r9wr9smrms9u33r FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 Q   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT fkbyfgloj439r9wr9smrms9u33r;
       public          postgres    false    4656    219    220            �   Y   x�341531���5�46�t1051r42�4202�50�50�424646�8��(���=?�*1'������������X����� ��b���� �C�      �   +   x�3���q�prt����2��@8�Nh�kW� 1r�      �   g   x�3�LL���33p��/�M���K�υ�%&�@&�*F�*�*�A�.n�e��%9��Q�E�����y���ni9�%�z��%�&I�\1z\\\ � �      �      x�3�4�2�4����� ��     