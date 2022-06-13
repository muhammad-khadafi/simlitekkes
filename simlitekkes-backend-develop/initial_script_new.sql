-- public.bagian_usulan definition

-- Drop table

DROP TABLE IF EXISTS public.bagian_usulan CASCADE;

CREATE TABLE public.bagian_usulan (
	id serial NOT NULL,
	nama varchar(100) NULL,
	urutan_tab int2 NULL,
	CONSTRAINT bagian_usulan_pkey PRIMARY KEY (id)
);


-- public.borang_review definition

-- Drop table

DROP TABLE IF EXISTS public.borang_review CASCADE;

CREATE TABLE public.borang_review (

);


-- public.file definition

-- Drop table

DROP TABLE IF EXISTS public.file CASCADE;

CREATE TABLE public.file (
	id bigserial NOT NULL,
	file_name varchar(100) NULL,
	file_path varchar(500) NULL,
	CONSTRAINT file_id_pk PRIMARY KEY (id)
);


-- public.jenis_dokumen definition

-- Drop table

DROP TABLE IF EXISTS public.jenis_dokumen CASCADE;

CREATE TABLE public.jenis_dokumen (
	id serial NOT NULL,
	nama varchar(100) NULL,
	kelompok_dokumen bpchar(1) NOT NULL,
	CONSTRAINT jenis_dokumen_penelitian_pkey PRIMARY KEY (id)
);


-- public.jenis_mitra definition

-- Drop table

DROP TABLE IF EXISTS public.jenis_mitra CASCADE;

CREATE TABLE public.jenis_mitra (
	id serial NOT NULL,
	nama varchar(255) NULL,
	CONSTRAINT jenis_mitra_pkey PRIMARY KEY (id)
);


-- public.jenis_output definition

-- Drop table

DROP TABLE IF EXISTS public.jenis_output CASCADE;

CREATE TABLE public.jenis_output (
	id serial NOT NULL,
	kelompok_output varchar(100) NULL,
	nama varchar(200) NULL,
	CONSTRAINT jenis_publikasi_pkey PRIMARY KEY (id)
);


-- public.jenis_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.jenis_penelitian CASCADE;

CREATE TABLE public.jenis_penelitian (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT jenis_penelitian_pkey PRIMARY KEY (id)
);


-- public.kategori_skema_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.kategori_skema_penelitian CASCADE;

CREATE TABLE public.kategori_skema_penelitian (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT kategori_skema_penelitian_pkey PRIMARY KEY (id)
);


-- public.kelompok_tkt definition

-- Drop table

DROP TABLE IF EXISTS public.kelompok_tkt CASCADE;

CREATE TABLE public.kelompok_tkt (
	id serial NOT NULL,
	nama varchar(100) NULL,
	is_active bool NULL,
	tahun bpchar(4) NULL,
	CONSTRAINT kelompok_tkt_pkey PRIMARY KEY (id)
);


-- public.komentar definition

-- Drop table

DROP TABLE IF EXISTS public.komentar CASCADE;

CREATE TABLE public.komentar (

);


-- public.komponen_rab definition

-- Drop table

DROP TABLE IF EXISTS public.komponen_rab CASCADE;

CREATE TABLE public.komponen_rab (
	id serial NOT NULL,
	nama varchar(100) NULL,
	deskripsi text NULL,
	CONSTRAINT jenis_pembelanjaan_penelitian_pkey PRIMARY KEY (id)
);


-- public.konfigurasi definition

-- Drop table

DROP TABLE IF EXISTS public.konfigurasi CASCADE;

CREATE TABLE public.konfigurasi (

);


-- public.log_aktivitas definition

-- Drop table

DROP TABLE IF EXISTS public.log_aktivitas CASCADE;

CREATE TABLE public.log_aktivitas (
	username varchar(100) NULL,
	timestamp_aktivitas timestamp NULL,
	"method" varchar(255) NULL,
	request text NULL,
	url text NULL
);
CREATE INDEX log_aktivitas_username ON public.log_aktivitas USING btree (username);


-- public.peran_peneliti definition

-- Drop table

DROP TABLE IF EXISTS public.peran_peneliti CASCADE;

CREATE TABLE public.peran_peneliti (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT peran_peneliti_pkey primary key (id)
);


-- public.peran_penulis_output definition

-- Drop table

DROP TABLE IF EXISTS public.peran_penulis_output CASCADE;

CREATE TABLE public.peran_penulis_output (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT peran_penulis_publikasi_pkey PRIMARY KEY (id)
);


-- public.pilihan_borang_review definition

-- Drop table

DROP TABLE IF EXISTS public.pilihan_borang_review CASCADE;

CREATE TABLE public.pilihan_borang_review (

);


-- public."role" definition

-- Drop table

DROP TABLE IF EXISTS public."role" CASCADE;

CREATE TABLE public."role" (
	id bigserial NOT NULL,
	role_code varchar(50) NULL,
	role_name varchar(100) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);


-- public.status_peneliti definition

-- Drop table

DROP TABLE IF EXISTS public.status_peneliti CASCADE;

CREATE TABLE public.status_peneliti (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT status_peneliti_pkey PRIMARY KEY (id)
);


-- public.tahap_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.tahap_penelitian CASCADE;

CREATE TABLE public.tahap_penelitian (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT tahap_penelitian_pkey PRIMARY KEY (id)
);


-- public.tema definition

-- Drop table

DROP TABLE IF EXISTS public.tema CASCADE;

CREATE TABLE public.tema (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT tema_pkey PRIMARY KEY (id)
);


-- public.tipe_user definition

-- Drop table

DROP TABLE IF EXISTS public.tipe_user CASCADE;

CREATE TABLE public.tipe_user (

);


-- public.komponen_penilaian definition

-- Drop table

DROP TABLE IF EXISTS public.komponen_penilaian CASCADE;

CREATE TABLE public.komponen_penilaian (
	id serial NOT NULL,
	nama varchar(100) NULL,
	id_tahap_penelitian int4 NULL,
	CONSTRAINT komponen_penilaian_pkey PRIMARY KEY (id),
	CONSTRAINT komponen_penilaian_id_tahap_penelitian_fkey FOREIGN KEY (id_tahap_penelitian) REFERENCES tahap_penelitian(id)
);


-- public.menu definition

-- Drop table

DROP TABLE IF EXISTS public.menu CASCADE;

CREATE TABLE public.menu (
	id bigserial NOT NULL,
	id_parent int8 NULL,
	menu_code varchar(50) NULL,
	menu_name varchar(100) NULL,
	menu_order int4 NULL,
	is_displayed bool NULL,
	icon varchar(100) NULL,
	router_to varchar(200) NULL,
	CONSTRAINT menu_pkey PRIMARY KEY (id),
	CONSTRAINT menu_id_parent_fkey FOREIGN KEY (id_parent) REFERENCES menu(id)
);


-- public.role_has_menu definition

-- Drop table

DROP TABLE IF EXISTS public.role_has_menu CASCADE;

CREATE TABLE public.role_has_menu (
	id bigserial NOT NULL,
	id_role int8 NULL,
	id_menu int8 NULL,
	CONSTRAINT role_has_menu_pkey PRIMARY KEY (id),
	CONSTRAINT role_has_menu_id_menu_fkey FOREIGN KEY (id_menu) REFERENCES menu(id),
	CONSTRAINT role_has_menu_id_role_fkey FOREIGN KEY (id_role) REFERENCES role(id)
);


-- public.rumpun_ilmu definition

-- Drop table

DROP TABLE IF EXISTS public.rumpun_ilmu CASCADE;

CREATE TABLE public.rumpun_ilmu (
	id serial NOT NULL,
	id_parent_rumpun_ilmu int4 NULL,
	kode bpchar(3) NULL,
	nama varchar(100) NULL,
	"level" int2 NULL,
	CONSTRAINT rumpun_ilmu_pkey PRIMARY KEY (id),
	CONSTRAINT rumpun_ilmu_id_parent_rumpun_ilmu_fkey FOREIGN KEY (id_parent_rumpun_ilmu) REFERENCES rumpun_ilmu(id)
);


-- public.skema_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.skema_penelitian CASCADE;

CREATE TABLE public.skema_penelitian (
	id serial NOT NULL,
	id_jenis_penelitian int4 NULL,
	nama varchar(100) NULL,
	singkatan varchar(10) NULL,
	deskripsi text NULL,
	klasifikasi_1 bool NULL,
	klasifikasi_2 bool NULL,
	klasifikasi_3 bool NULL,
	persyaratan_penelitian text NULL,
	tkt_min int4 NULL,
	tkt_max int4 NULL,
	jumlah_pengusul_min int2 NULL,
	jumlah_pengusul_max int2 NULL,
	jangka_waktu_min int2 NULL,
	jangka_waktu_max int2 NULL,
	jumlah_dana_min int4 NULL,
	jumlah_dana_max int4 NULL,
	id_kategori_skema_penelitian int4 NULL,
	is_dikelola_pusat bool NULL,
	CONSTRAINT skema_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT skema_penelitian_id_jenis_penelitian_fkey FOREIGN KEY (id_jenis_penelitian) REFERENCES jenis_penelitian(id),
	CONSTRAINT skema_penelitian_id_kategori_skema_penelitian_fkey FOREIGN KEY (id_kategori_skema_penelitian) REFERENCES kategori_skema_penelitian(id)
);


-- public.status_tahap_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.status_tahap_penelitian CASCADE;

CREATE TABLE public.status_tahap_penelitian (
	id serial NOT NULL,
	id_status_syarat_1 int4 NULL,
	id_tahap_penelitian int4 NULL,
	nama varchar(100) NULL,
	id_status_syarat_2 int4 NULL,
	kode_status varchar(20) NULL,
	kode_role_1 varchar(20) NULL,
	kode_role_2 varchar(20) NULL,
	label_button varchar(20) NULL,
	CONSTRAINT status_tahap_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT status_tahap_penelitian_id_status_syarat_2_fkey FOREIGN KEY (id_status_syarat_2) REFERENCES status_tahap_penelitian(id),
	CONSTRAINT status_tahap_penelitian_id_status_syarat_fkey FOREIGN KEY (id_status_syarat_1) REFERENCES status_tahap_penelitian(id),
	CONSTRAINT status_tahap_penelitian_id_tahap_penelitian_fkey FOREIGN KEY (id_tahap_penelitian) REFERENCES tahap_penelitian(id)
);


-- public.tingkat_kesiapan_teknologi definition

-- Drop table

DROP TABLE IF EXISTS public.tingkat_kesiapan_teknologi CASCADE;

CREATE TABLE public.tingkat_kesiapan_teknologi (
	id serial NOT NULL,
	"level" int2 NULL,
	definisi text NULL,
	id_kelompok_tkt int4 NULL,
	CONSTRAINT tingkat_kesiapan_teknologi_pkey PRIMARY KEY (id),
	CONSTRAINT tingkat_kesiapan_teknologi_id_kelompok_tkt_fkey FOREIGN KEY (id_kelompok_tkt) REFERENCES kelompok_tkt(id)
);


-- public.topik definition

-- Drop table

DROP TABLE IF EXISTS public.topik CASCADE;

CREATE TABLE public.topik (
	id serial NOT NULL,
	id_tema int4 NULL,
	nama varchar(250) NULL,
	is_active bool NULL,
	CONSTRAINT topik_pkey PRIMARY KEY (id),
	CONSTRAINT topik_id_tema_fkey FOREIGN KEY (id_tema) REFERENCES tema(id)
);


-- public.indikator_tkt definition

-- Drop table

DROP TABLE IF EXISTS public.indikator_tkt CASCADE;

CREATE TABLE public.indikator_tkt (
	id serial NOT NULL,
	id_tingkat_kesiapan_teknologi int4 NULL,
	deskripsi varchar(500) NULL,
	nomor_urut int2 NULL,
	CONSTRAINT indikator_tkt_pkey PRIMARY KEY (id),
	CONSTRAINT indikator_tkt_id_tingkat_kesiapan_teknologi_fkey FOREIGN KEY (id_tingkat_kesiapan_teknologi) REFERENCES tingkat_kesiapan_teknologi(id)
);


-- public.kriteria_penilaian definition

-- Drop table

DROP TABLE IF EXISTS public.kriteria_penilaian CASCADE;

CREATE TABLE public.kriteria_penilaian (
	id serial NOT NULL,
	deskripsi varchar(250) NULL,
	id_komponen_penilaian int4 NULL,
	skor_maksimal int4 NULL,
	id_skema_penelitian int4 NULL,
	id_bagian_usulan int4 NULL,
	is_active bool NOT NULL DEFAULT true,
	CONSTRAINT kriteria_penilaian_pkey PRIMARY KEY (id),
	CONSTRAINT kriteria_penilaian_id_bagian_usulan_fkey FOREIGN KEY (id_bagian_usulan) REFERENCES bagian_usulan(id),
	CONSTRAINT kriteria_penilaian_id_komponen_penillaian_fkey FOREIGN KEY (id_komponen_penilaian) REFERENCES komponen_penilaian(id),
	CONSTRAINT kriteria_penilaian_id_skema_penelitian_fkey FOREIGN KEY (id_skema_penelitian) REFERENCES skema_penelitian(id)
);


-- public.organisasi definition

-- Drop table

DROP TABLE IF EXISTS public.organisasi CASCADE;

CREATE TABLE public.organisasi (
	id serial NOT NULL,
	id_topik int4 NULL,
	nama varchar(100) NULL,
	no_sk_pendirian varchar(100) NULL,
	alamat varchar(100) NULL,
	no_telp varchar(20) NULL,
	no_fax varchar(20) NULL,
	email varchar(50) NULL,
	nama_jabatan_kepala varchar(100) NULL,
	nidn_pejabat_kepala varchar(100) NULL,
	nama_pejabat_kepala varchar(100) NULL,
	jenis_kelamin bpchar(1) NULL,
	jenjang_pendidikan varchar(100) NULL,
	is_poltekkes bool NULL,
	klasifikasi int2 NULL,
	website varchar(250) NULL,
	kode_pddikti varchar(10) NULL,
	CONSTRAINT organisasi_pkey PRIMARY KEY (id),
	CONSTRAINT organisasi_id_topik_fkey FOREIGN KEY (id_topik) REFERENCES topik(id)
);


-- public.output_skema_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.output_skema_penelitian CASCADE;

CREATE TABLE public.output_skema_penelitian (
	id serial NOT NULL,
	id_skema_penelitian int4 NULL,
	id_jenis_output int4 NULL,
	tahun int2 NULL,
	CONSTRAINT output_skema_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT output_skema_penelitian_id_jenis_output_fkey FOREIGN KEY (id_jenis_output) REFERENCES jenis_output(id),
	CONSTRAINT output_skema_penelitian_id_skema_penelitian_fkey FOREIGN KEY (id_skema_penelitian) REFERENCES skema_penelitian(id)
);


-- public.pilihan_kriteria_penilaian definition

-- Drop table

DROP TABLE IF EXISTS public.pilihan_kriteria_penilaian CASCADE;

CREATE TABLE public.pilihan_kriteria_penilaian (
	id serial NOT NULL,
	id_kriteria_penilaian int4 NOT NULL,
	deskripsi varchar(250) NOT NULL,
	nilai float8 NOT NULL,
	nomor_urut int4 NOT NULL,
	CONSTRAINT pilihan_kriteria_penilaian_id PRIMARY KEY (id),
	CONSTRAINT pilihan_kriteria_penilaian_id_kriteria_penilaian_fkey FOREIGN KEY (id_kriteria_penilaian) REFERENCES kriteria_penilaian(id)
);


-- public.userweb definition

-- Drop table

DROP TABLE IF EXISTS public.userweb CASCADE;


CREATE TABLE public.userweb (
	id serial NOT NULL,
	username varchar(100) NULL,
	"password" varchar(100) NULL,
	last_login timestamp NULL,
	foto varchar(100) NULL,
	nip varchar(25) NULL,
	nama varchar(100) NULL,
	email varchar(100) NULL,
	is_active bool NULL,
	id_organisasi int4 NULL,
	foto_name varchar(100) NULL,
	nitk varchar(25) NULL,
	nidn varchar(25) NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);


-- public.userweb_has_role definition

-- Drop table

DROP TABLE IF EXISTS public.userweb_has_role CASCADE;

CREATE TABLE public.userweb_has_role (
	id bigserial NOT NULL,
	id_userweb int8 NULL,
	id_role int8 NULL,
	CONSTRAINT userweb_has_role_pkey PRIMARY KEY (id),
	CONSTRAINT userweb_has_role_id_role_fkey FOREIGN KEY (id_role) REFERENCES role(id),
	CONSTRAINT userweb_has_role_id_userweb_fkey FOREIGN KEY (id_userweb) REFERENCES userweb(id)
);


-- public.berita definition

-- Drop table

DROP TABLE IF EXISTS public.berita CASCADE;

CREATE TABLE public.berita (
	id serial NOT NULL,
	id_organisasi int4 NULL,
	judul varchar(250) NULL,
	isi text NULL,
	is_published bool NULL DEFAULT true,
	user_publish varchar(100) NULL,
	publish_timestamp timestamp NULL DEFAULT now(),
	CONSTRAINT berita_pkey PRIMARY KEY (id),
	CONSTRAINT berita_id_organisasi_fkey FOREIGN KEY (id_organisasi) REFERENCES organisasi(id)
);


-- public.dokumen_organisasi definition

-- Drop table

DROP TABLE IF EXISTS public.dokumen_organisasi CASCADE;

CREATE TABLE public.dokumen_organisasi (
	id serial NOT NULL,
	id_organisasi int4 NULL,
	nama_file varchar(250) NULL,
	file_path varchar(250) NULL,
	timestamp_upload timestamp NULL,
	user_upload varchar(100) NULL,
	id_jenis_dokumen int4 NULL,
	nomor_dokumen varchar(100) NULL,
	tahun_terbit varchar(4) NULL,
	judul varchar(250) NULL,
	CONSTRAINT dokumen_organisasi_pkey PRIMARY KEY (id),
	CONSTRAINT dokumen_organisasi_id_jenis_dokumen_fkey FOREIGN KEY (id_jenis_dokumen) REFERENCES jenis_dokumen(id),
	CONSTRAINT dokumen_organisasi_id_organisasi_fkey FOREIGN KEY (id_organisasi) REFERENCES organisasi(id)
);


-- public.peneliti definition

-- Drop table

DROP TABLE IF EXISTS public.peneliti CASCADE;

CREATE TABLE public.peneliti (
	id serial NOT NULL,
	id_user int4 NULL,
	id_bidang_keahlian int4 NULL,
	id_organisasi int4 NULL,
	nama varchar(100) NULL,
	nidn varchar(100) NULL,
	beban_review_maksimal int4 NULL,
	program_studi varchar NULL,
	jenjang_pendidikan_terakhir varchar NULL,
	jabatan_akademik_fungsional varchar NULL,
	gelar_depan varchar(100) NULL,
	gelar_belakang varchar(100) NULL,
	tempat_lahir varchar(100) NULL,
	tanggal_lahir date NULL,
	no_ktp bpchar(16) NULL,
	no_telp varchar(20) NULL,
	no_hp varchar(20) NULL,
	email varchar(100) NULL,
	website_personal varchar(100) NULL,
	alamat varchar(100) NULL,
	last_sync_time timestamp NULL,
	is_active bool NULL,
	jenis_kelamin bpchar(1) NULL,
	is_trained bool NULL,
	nomor_sertifikasi varchar(20) NULL,
	CONSTRAINT peneliti_pkey PRIMARY KEY (id),
	CONSTRAINT peneliti_id_bidang_keahlian_fkey FOREIGN KEY (id_bidang_keahlian) REFERENCES rumpun_ilmu(id),
	CONSTRAINT peneliti_id_organisasi_fkey FOREIGN KEY (id_organisasi) REFERENCES organisasi(id),
	CONSTRAINT peneliti_id_user_fkey FOREIGN KEY (id_user) REFERENCES userweb(id)
);


-- public.reviewer_skema definition

-- Drop table

DROP TABLE IF EXISTS public.reviewer_skema CASCADE;

CREATE TABLE public.reviewer_skema (
	id serial NOT NULL,
	id_peneliti int4 NULL,
	id_organisasi int4 NULL,
	id_pemberi_tugas int4 NULL,
	tahun_usulan bpchar(4) NULL,
	tahun_pelaksanaan bpchar(4) NULL,
	is_active bool NULL,
	is_reviewer_eksternal bool NULL,
	is_new bool NULL,
	is_seen bool NULL,
	id_dokumen_organisasi int4 NULL,
	CONSTRAINT reviewer_skema_id PRIMARY KEY (id),
	CONSTRAINT reviewer_skema_dokumen_organisasi_fk FOREIGN KEY (id_dokumen_organisasi) REFERENCES dokumen_organisasi(id),
	CONSTRAINT reviewer_skema_id_organisasi_fkey FOREIGN KEY (id_organisasi) REFERENCES organisasi(id),
	CONSTRAINT reviewer_skema_id_pemberi_tugas_fkey FOREIGN KEY (id_pemberi_tugas) REFERENCES userweb(id),
	CONSTRAINT reviewer_skema_id_peneliti_fkey FOREIGN KEY (id_peneliti) REFERENCES peneliti(id)
);


-- public.penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.penelitian CASCADE;

CREATE TABLE public.penelitian (
	id serial NOT NULL,
	id_topik int4 NULL,
	id_skema_penelitian int4 NULL,
	id_rumpun_ilmu int4 NULL,
	id_tingkat_kesiapan_teknologi int4 NULL,
	id_tingkat_kesiapan_teknologi_target int4 NULL,
	judul varchar(250) NULL,
	ringkasan text NULL,
	tahun_usulan bpchar(4) NULL,
	tahun_mulai_pelaksanaan bpchar(4) NULL,
	tahun_selesai_pelaksanaan bpchar(4) NULL,
	latar_belakang text NULL,
	tinjauan_pustaka text NULL,
	metode text NULL,
	daftar_pustaka text NULL,
	id_reviewer_skema int4 NULL,
	lokasi_penelitian varchar(250) NULL,
	id_kelompok_tkt int4 NULL,
	CONSTRAINT penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT penelitian_id_kelompok_tkt_fkey FOREIGN KEY (id_kelompok_tkt) REFERENCES kelompok_tkt(id),
	CONSTRAINT penelitian_id_reviewer_skema_fkey FOREIGN KEY (id_reviewer_skema) REFERENCES reviewer_skema(id),
	CONSTRAINT penelitian_id_rumpun_ilmu_fkey FOREIGN KEY (id_rumpun_ilmu) REFERENCES rumpun_ilmu(id),
	CONSTRAINT penelitian_id_skema_penelitian_fkey FOREIGN KEY (id_skema_penelitian) REFERENCES skema_penelitian(id),
	CONSTRAINT penelitian_id_tingkat_kesiapan_teknologi_fkey FOREIGN KEY (id_tingkat_kesiapan_teknologi) REFERENCES tingkat_kesiapan_teknologi(id),
	CONSTRAINT penelitian_id_tingkat_kesiapan_teknologi_target_fkey FOREIGN KEY (id_tingkat_kesiapan_teknologi_target) REFERENCES tingkat_kesiapan_teknologi(id),
	CONSTRAINT penelitian_id_topik_fkey FOREIGN KEY (id_topik) REFERENCES topik(id)
);


-- public.rencana_anggaran definition

-- Drop table

DROP TABLE IF EXISTS public.rencana_anggaran CASCADE;

CREATE TABLE public.rencana_anggaran (
	id serial NOT NULL,
	id_penelitian int4 NULL,
	id_komponen_rab int4 NULL,
	tahun int2 NULL,
	item varchar(100) NULL,
	volume numeric NULL,
	satuan varchar(10) NULL,
	biaya_satuan numeric NULL,
	CONSTRAINT rencana_anggaran_pkey PRIMARY KEY (id),
	CONSTRAINT rencana_anggaran_id_jenis_pembelanjaan_penelitian_fkey FOREIGN KEY (id_komponen_rab) REFERENCES komponen_rab(id),
	CONSTRAINT rencana_anggaran_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.target_output_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.target_output_penelitian CASCADE;

CREATE TABLE public.target_output_penelitian (
	id serial NOT NULL,
	id_penelitian int4 NULL,
	tahun_target varchar(4) NULL,
	tahun_realisasi varchar(4) NULL,
	is_wajib bool NOT NULL DEFAULT false,
	is_tambahan bool NOT NULL DEFAULT false,
	id_jenis_output int4 NULL,
	CONSTRAINT checklist_output_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT checklist_output_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id),
	CONSTRAINT target_output_penelitian_id_jenis_output_fkey FOREIGN KEY (id_jenis_output) REFERENCES jenis_output(id)
);


-- public.tim_peneliti definition

-- Drop table

DROP TABLE IF EXISTS public.tim_peneliti CASCADE;

CREATE TABLE public.tim_peneliti (
	id serial NOT NULL,
	id_peneliti int4 NULL,
	id_penelitian int4 NULL,
	id_peran_peneliti int4 NULL,
	id_status_peneliti int4 NULL,
	id_organisasi int4 NULL,
	id_bidang_keahlian_saat_ini int4 NULL,
	tugas_penelitian varchar(250) NULL,
	alokasi_jam_per_minggu numeric NULL,
	CONSTRAINT tim_peneliti_pkey PRIMARY KEY (id),
	CONSTRAINT tim_peneliti_id_organisasi_fkey FOREIGN KEY (id_organisasi) REFERENCES organisasi(id),
	CONSTRAINT tim_peneliti_id_peneliti_fkey FOREIGN KEY (id_peneliti) REFERENCES peneliti(id),
	CONSTRAINT tim_peneliti_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id),
	CONSTRAINT tim_peneliti_id_peran_peneliti_fkey FOREIGN KEY (id_peran_peneliti) REFERENCES peran_peneliti(id),
	CONSTRAINT tim_peneliti_id_status_peneliti_fkey FOREIGN KEY (id_status_peneliti) REFERENCES status_peneliti(id)
);


-- public.workflow_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.workflow_penelitian CASCADE;

CREATE TABLE public.workflow_penelitian (
	id_penelitian int4 NOT NULL,
	id_status_tahap_penelitian int4 NOT NULL,
	timestamp_update timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	is_last bool NOT NULL DEFAULT false,
	username varchar(100) NOT NULL,
	CONSTRAINT workflow_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id),
	CONSTRAINT workflow_penelitian_id_status_tahap_penelitian_fkey FOREIGN KEY (id_status_tahap_penelitian) REFERENCES status_tahap_penelitian(id)
);


-- public.catatan_harian definition

-- Drop table

DROP TABLE IF EXISTS public.catatan_harian CASCADE;

CREATE TABLE public.catatan_harian (
	id serial NOT NULL,
	id_penelitian int4 NULL,
	tanggal_pelaksanaan date NULL,
	isi text NULL,
	dana_terpakai numeric NULL,
	persentase numeric NULL,
	file_path varchar(250) NULL,
	timestamp_upload timestamp NULL,
	CONSTRAINT catatan_harian_pkey PRIMARY KEY (id),
	CONSTRAINT catatan_harian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.dokumen_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.dokumen_penelitian CASCADE;

CREATE TABLE public.dokumen_penelitian (
	id serial NOT NULL,
	id_jenis_dokumen int4 NULL,
	id_penelitian int4 NULL,
	judul varchar(255) NULL,
	file_path varchar(100) NULL,
	tanggal_maksimal_upload timestamp NULL,
	CONSTRAINT dokumen_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT dokumen_penelitian_id_jenis_dokumen_fkey FOREIGN KEY (id_jenis_dokumen) REFERENCES jenis_dokumen(id),
	CONSTRAINT dokumen_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.jadwal_kegiatan definition

-- Drop table

DROP TABLE IF EXISTS public.jadwal_kegiatan CASCADE;

CREATE TABLE public.jadwal_kegiatan (
	id serial NOT NULL,
	id_penelitian int4 NULL,
	tanggal_awal_jadwal date NULL,
	tanggal_akhir_jadwal date NULL,
	tahun int2 NULL,
	nama_kegiatan varchar(255) NULL,
	CONSTRAINT jadwal_kegiatan_pkey PRIMARY KEY (id),
	CONSTRAINT jadwal_kegiatan_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.kriteria_penilaian_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.kriteria_penilaian_penelitian CASCADE;

CREATE TABLE public.kriteria_penilaian_penelitian (
	id_penelitian int4 NOT NULL,
	id_kriteria_penilaian int4 NULL,
	id_pilihan_kriteria_penilaian int4 NULL,
	komentar varchar(250) NULL,
	CONSTRAINT kriteria_penilaian_penelitian_id_penelitian_id_kriteria_penilai UNIQUE (id_penelitian, id_kriteria_penilaian),
	CONSTRAINT kriteria_penilaian_penelitian_id_kriteria_penilaian_fkey FOREIGN KEY (id_kriteria_penilaian) REFERENCES kriteria_penilaian(id),
	CONSTRAINT kriteria_penilaian_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id),
	CONSTRAINT kriteria_penilaian_penelitian_id_pilihan_kriteria_penilaia_fkey FOREIGN KEY (id_pilihan_kriteria_penilaian) REFERENCES pilihan_kriteria_penilaian(id)
);


-- public.mitra_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.mitra_penelitian CASCADE;

CREATE TABLE public.mitra_penelitian (
	id serial NOT NULL,
	id_jenis_mitra int4 NULL,
	id_penelitian int4 NULL,
	nama_mitra varchar(100) NULL,
	email varchar(100) NULL,
	penanggung_jawab varchar(100) NULL,
	file_path varchar(100) NULL,
	timestamp_upload timestamp NULL,
	CONSTRAINT mitra_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT mitra_penelitian_id_jenis_mitra_fkey FOREIGN KEY (id_jenis_mitra) REFERENCES jenis_mitra(id),
	CONSTRAINT mitra_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public."output" definition

-- Drop table

DROP TABLE IF EXISTS public."output" CASCADE;

CREATE TABLE public."output" (
	id serial NOT NULL,
	id_jenis_output int4 NULL,
	id_penelitian int4 NULL,
	judul varchar(255) NULL,
	path_file varchar(255) NULL,
	timestamp_upload timestamp NULL,
	media_publikasi varchar(255) NULL,
	tanggal_publikasi date NULL,
	url varchar(100) NULL,
	CONSTRAINT publikasi_pkey PRIMARY KEY (id),
	CONSTRAINT publikasi_id_jenis_publikasi_fkey FOREIGN KEY (id_jenis_output) REFERENCES jenis_output(id),
	CONSTRAINT publikasi_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.penulis_output definition

-- Drop table

DROP TABLE IF EXISTS public.penulis_output CASCADE;

CREATE TABLE public.penulis_output (
	id serial NOT NULL,
	id_peran_penulis_output int4 NULL,
	id_output int4 NULL,
	id_peneliti int4 NULL,
	CONSTRAINT penulis_publikasi_pkey PRIMARY KEY (id),
	CONSTRAINT penulis_publikasi_id_peneliti_fkey FOREIGN KEY (id_peneliti) REFERENCES peneliti(id),
	CONSTRAINT penulis_publikasi_id_peran_penulis_publikasi_fkey FOREIGN KEY (id_peran_penulis_output) REFERENCES peran_penulis_output(id),
	CONSTRAINT penulis_publikasi_id_publikasi_fkey FOREIGN KEY (id_output) REFERENCES output(id)
);


-- public.prosiding definition

-- Drop table

DROP TABLE IF EXISTS public.prosiding CASCADE;

CREATE TABLE public.prosiding (
	id_output int4 NULL,
	nama_prosiding varchar(100) NULL,
	tahun_prosiding bpchar(4) NULL,
	CONSTRAINT prosiding_id_publikasi_fkey FOREIGN KEY (id_output) REFERENCES output(id)
);


-- public.buku definition

-- Drop table

DROP TABLE IF EXISTS public.buku CASCADE;

CREATE TABLE public.buku (
	id_output int4 NULL,
	nama_penerbit varchar(100) NULL,
	tahun_terbit bpchar(4) NULL,
	isbn varchar(25) NULL,
	jumlah_halaman int4 NULL,
	CONSTRAINT buku_id_publikasi_fkey FOREIGN KEY (id_output) REFERENCES output(id)
);


-- public.hki definition

-- Drop table

DROP TABLE IF EXISTS public.hki CASCADE;

CREATE TABLE public.hki (
	id_output int4 NULL,
	tahun_pelaksanaan bpchar(4) NULL,
	no_pendaftaran varchar(100) NULL,
	status_hki bpchar(1) NULL,
	no_hki varchar(100) NULL,
	CONSTRAINT hki_id_publikasi_fkey FOREIGN KEY (id_output) REFERENCES output(id)
);


-- public.jurnal definition

-- Drop table

DROP TABLE IF EXISTS public.jurnal CASCADE;

CREATE TABLE public.jurnal (
	id_output int4 NULL,
	jurnal varchar NULL,
	tahun_terbit bpchar(4) NULL,
	volume varchar(25) NULL,
	issn varchar(25) NULL,
	nomor varchar(10) NULL,
	CONSTRAINT jurnal_id_publikasi_fkey FOREIGN KEY (id_output) REFERENCES output(id)
);


-- public.kontribusi_mitra definition

-- Drop table

DROP TABLE IF EXISTS public.kontribusi_mitra CASCADE;

CREATE TABLE public.kontribusi_mitra (
	id serial NOT NULL,
	id_mitra_penelitian int4 NULL,
	tahun int2 NULL,
	jumlah_kontribusi int4 NULL,
	barang_kontribusi text NULL,
	CONSTRAINT kontribusi_mitra_pkey PRIMARY KEY (id),
	CONSTRAINT kontribusi_mitra_id_mitra_penelitian_fkey FOREIGN KEY (id_mitra_penelitian) REFERENCES mitra_penelitian(id)
);

-- ********** Insert Master Data ************

INSERT INTO public.kelompok_tkt (id,nama,is_active,tahun) VALUES 
(4,'TKT Jenis Sosial Humaniora dan Pendidikan',NULL,NULL)
,(3,'TKT Jenis Farmasi',true,'2019')
,(10,'TKT - Test ke 1',true,'2019')
,(1,'TKT Jenis Kesehatan – Produk Vaksin/Hayati',true,'2020')
,(2,'TKT Jenis Kesehatan – Produk Alat Kesehatan',true,'2020')
,(13,'TKT - Validasi Kesehatan',true,'2020')
;

INSERT INTO public.tema (id,nama) VALUES 
(1,'Teknologi Produk Biofarmasetika')
,(2,'Teknologi Alat Kesehatan dan Diagnostik')
,(3,'Teknologi Kemandirian Bahan Baku Obat')
,(7,'Pelayanan Kesehatan Dasar yang Berkualitas')
,(4,'Akses Pelayanan Kesehatan Ibu, Anak, Remaja, dan Lanjut Usia yang Berkualitas')
,(5,'Gizi')
,(6,'Pengendalian Penyakit dan Penyehatan Lingkungan')
,(8,'Pelayanan Kesehatan Rujukan yang Berkualitas')
,(9,'Obat dan Makanan')
,(10,'Sumber Daya Manusia Kesehatan')
,(11,'Promosi Kesehatan dan Pemberdayaan Masyarakat')
,(12,'Pembiayaan Kesehatan')
,(13,'Pengembangan Pendidikan Kesehatan')
,(14,'Tema di Reviewer')
;

INSERT INTO public.topik (id,id_tema,nama,is_active) VALUES 
(1,1,'Penguasaan produksi vaksin utama',true)
,(2,2,'Pengembangan in vivo diagnostic (IVD) untuk deteksi penyakit infeksi;',true)
,(3,2,'Prototipe diagnostic kit untuk penyakit  degenerative;',true)
,(4,2,'Pengembangan alat elektromedik.',true)
,(5,3,'Pengembangan fitofarmaka berbasis sumber daya local;',true)
,(6,3,'Bahan baku obat kimia;',true)
,(7,3,'Saintifikasi jamu & herbal, teknologi produksi pigmen alami.',true)
,(8,4,'Teknologi tepat guna dalam bidang Kebidanan (remaja, prakonsepsi, kehamilan, persalinan, nifas, bayi baru lahir, anak, Kespro/KB, lansia);',true)
,(9,4,'Metode /Media promkes bidang Kebidanan/KIA;',true)
,(10,4,'Model pelayanan kebidanan/KIA;',true)
,(11,4,'Prosedur dalam pelayanan Kebidanan/KIA;',true)
,(12,4,'Upaya preventif dan promotif dalam Kebidanan/Kesehatan Ibu dan anak ;',true)
,(13,4,'Pelayanan/pengobatan komplementer dalam Kebidanan/KIA;',true)
,(16,5,'Gizi masyarakat;',true)
,(17,5,'Gizi klinik;',true)
,(18,5,'Penyelenggaraan makanan;',true)
,(19,5,'Keamanan pangan;',true)
,(20,5,'Pengembangan produk pangan.',true)
,(21,6,'Pelayanan kesehatan lingkungan;',true)
,(22,6,'Penyehatan makanan, air,udara dan tanah;',true)
,(23,6,'Pengelolaan sampah;',true)
,(24,6,'Pengendalian vektor;',true)
,(25,6,'Kesehatan kerja;',true)
,(26,6,'Pencegahan dan penanggulangan penyakit menular (tuberkulosis, malaria, dbd, dm.hipertensi,dll).',true)
,(27,7,'Tugas pokok dan fungsi Pelayanan Kesehatan Dasar;',true)
,(28,7,'Kelembagaan Pelayanan Kesehatan Dasar;',true)
,(29,7,'Kecukupan dan kelengkapan SDM Pelayanan Kesehatan Dasar;',true)
,(30,7,'Pengelolaan obat dan pelayanan kesehaatan di Pelayanan Kesehatan Dasar;',true)
,(31,7,'Pembiayaan Pelayanan Kesehatan Dasar;',true)
,(32,7,'Manajemen Pelayanan Kesehatan Dasar.',true)
,(33,8,'Tugas pokok dan fungsi Pelayanan Kesehatan Rujukan;',true)
,(34,8,'Kelembagaan Pelayanan Kesehatan Rujukan;',true)
,(35,8,'Kecukupan dan kelengkapan SDM Pelayanan Kesehatan Rujukan;',true)
,(36,8,'Pengelolaan obat dan pelayanan kesehaatan di Pelayanan Kesehatan Rujukan;',true)
,(37,8,'Pembiayaan Pelayanan Kesehatan Rujukan;',true)
,(38,8,'Manajemen Pelayanan Kesehatan Rujukan.',true)
,(39,9,'Toksikologi',true)
,(40,9,'Keamanan Pangan',true)
,(41,9,'Terapetik',true)
,(42,10,'Ketersediaan Sumber Daya Manusia Kesehatan;',true)
,(43,10,'Penyebaran Sumber Daya Manusia Kesehatan;',true)
,(44,10,'Mutu Sumber Daya Manusia Kesehatan.',true)
,(45,11,'Model pemberdayaan masyarakat (Sanitasi Total Berbasis Masyarakat, Model Pencegahan PTM, Inovasi Kesehatan Remaja, Inovasi Penggunaan IT dalam edukasi kesehatan, Inovasi Penggunaan IT untuk perbaikan manajemen, dll);',true)
,(46,11,'Intervensi kesehatan berbasis budaya lokal (dengan metode participatory action research), model transformasi sosial, dll.',true)
,(47,12,'Evaluasi JKN (Studi CEA, Evaluasi Paket Manfaat, dll);',true)
,(48,12,'Public private mix (Peran Swasta dalam pelayanan kesehatan, Studi disain UKP, Studi peran CSR, etc).',true)
,(49,13,'Input kegiatan pendidikan kesehatan;',true)
,(50,13,'Proses Kegiatan Pendidikan;',true)
,(51,13,'Luaran Kegiatan PendidikanModel pengembangan dan intervensi pendidikan kesehatan.',true)
,(62,5,'gizi klinik;',true)
,(63,5,'gizi klinik;',true)
,(14,4,'Imunisasi/Vaksin',true)
,(15,4,'Akses pelayanan kesehatan ibu dan anak.',true)
,(68,5,'gizi klinikk',NULL)
,(71,5,'asd',NULL)
,(72,5,'asdas',NULL)
,(73,5,'asdassdasd',NULL)
,(75,4,'Test - Sonia',false)
,(76,NULL,'asdsdfa',NULL)
;


ALTER TABLE public.organisasi ALTER COLUMN alamat TYPE varchar(1000) USING alamat::varchar;
ALTER TABLE public.rencana_anggaran ALTER COLUMN satuan TYPE varchar(20) USING satuan::varchar;

INSERT INTO public.organisasi (id,id_topik,nama,no_sk_pendirian,alamat,no_telp,no_fax,email,nama_jabatan_kepala,nidn_pejabat_kepala,nama_pejabat_kepala,jenis_kelamin,jenjang_pendidikan,is_poltekkes,klasifikasi,website,kode_pddikti) VALUES 
(1,3,'PPSDM','','Jl. Hang Jebat III Blok F3, Kebayoran Baru','0217245517, 72797302','021-72797302','ppsdm@gmail.com','Direktur','02.354.7535.324.00','drg. Ita','P','',false,1,'http://www.bppsdmk.kemkes.go.id/web/',NULL);


INSERT INTO public.userweb (id,username,"password",last_login,foto,nip,nama,email,is_active,id_organisasi,foto_name) VALUES 
(1,'superadmin','$2a$10$BPI9wg3sT.J826sbLhB.B./owLWCgBv40GRGyJKd.Yv6/mzJcKOiO','2020-02-01 00:00:00.000','file\userweb-foto\amazon-20200518111238.png','120735662728365','Sony Tulung','admin@admin.com',true,1,'amazon-20200518111238.png')
,(41,'adminppsdm','$2a$10$mq1bhn1BpTTsFnC9NL2ExObPcCZHG7PvxigQlLDGfBx0MIbzUa5Ba',NULL,'file\userweb-foto\kemenkes-ri-20200625162436.jpg','','Administrator PPSDM','sonia@pusilkom.com',true,1,'kemenkes-ri-20200625162436.jpg')
,(8,'pengelolapusat','$2a$10$.w5d0uQvqKnT/F.Lbi5g0uelDHsqBFG8r5deE5QxNoWwluHt5EWGK',NULL,NULL,'290620201718','Pengelola Pusat','pengelolapusat@gmail.com',true,1,NULL)
;

INSERT INTO public."role" (id,role_code,role_name) VALUES 
(3,'GUEST','Guest')
,(4,'EMPLOYEE','Employee')
,(5,'DIRECTOR','Director')
,(6,'ROLE1','Role1')
,(8,'OPERATOR_PPM','Operator PPM')
,(9,'PENGELOLA_PUSAT','Pengelola Pusat')
,(16,'GUEST2','Guest23')
,(24,'DOSEN','Dosen')
,(7,'KAPUS_PPM','Kapus PPM')
,(26,'ADMINISTRATOR','Administrator')
,(1,'ADMIN','Super Admin')
,(28,'REVIEWER','Reviewer')
,(29,'VALIDASI','Validasi')
,(30,'CAHGITU','Cah Gitu')
;

INSERT INTO public.userweb_has_role (id,id_userweb,id_role) VALUES 
(10,1,1)
,(31,8,9)
,(61,41,26)
;

INSERT INTO public.menu (id,id_parent,menu_code,menu_name,menu_order,is_displayed,icon,router_to) VALUES 
(80,NULL,'DAFTAR_REVIEW_PLOTTING','Daftar Review dan Plotting',2,true,'mdi-ballot-outline','')
,(7,NULL,'USER_MANAGEMENT','Manajemen Pengguna',3,true,'mdi-account','')
,(6,NULL,'DATA','Data',2,false,'mdi-cog-box',NULL)
,(27,NULL,'DATA_PENDUKUNG','Data Pendukung',2,false,'mdi-folder-open','')
,(33,NULL,'PENILAIAN_PRA','Pra Penilaian',4,true,'mdi-text-box-check-outline','')
,(36,NULL,'PENELITIAN','Penelitian',6,true,'mdi-square-edit-outline','')
,(88,NULL,'LAPORAN','Laporan',8,true,'mdi-file-chart','')
,(5,NULL,'HOME','Beranda',1,false,'mdi-home','')
,(100,NULL,'DATA_REFERENSI','Data Referensi',2,true,'mdi-text-box-check-outline','')
,(101,NULL,'DATA_MASTER','Data Master',3,true,'mdi-text-box-check-outline','')
,(105,NULL,'REVIEW','Review',5,true,'mdi-text-box-check-outline','')
,(102,NULL,'INFORMASI','Informasi',7,true,'mdi-text-box-check-outline','')
,(103,NULL,'AKUN','Akun',9,true,'mdi-text-box-check-outline','')
,(104,NULL,'PROFIL','Profil',10,true,'mdi-text-box-check-outline','')
,(39,36,'REKAP_USULAN','Rekap Usulan',9,true,'mdi-view-list','/penelitian/rekap')
,(49,39,'USULAN_PER_SKEMA_TAHUN','Usulan per Skema Tahun',1,NULL,'','/penelitian/usulan/:idSkemaPenelitian/:tahunUsulan')
,(50,49,'DETAIL_USULAN','Detail Usulan',1,NULL,'','/penelitian/:id/identitas-usulan')
,(81,105,'RIWAYAT_REVIEW','Riwayat Review',2,true,'mdi-clipboard-text-outline','/riwayat-review')
,(87,105,'DAFTAR_REVIEW','Daftar Review',1,true,'mdi-view-list','/review')
,(16,104,'PROFIL_POLTEKKES','Profil Poltekkes',6,true,'mdi-bank','/profil-poltekkes')
,(26,104,'CHANGE_PASSWORD','Ubah Password',3,true,'mdi-account-key','/change-password')
,(2,104,'PROFIL_PENGGUNA','Profil Pengguna',2,true,'mdi-account','/profil-pengguna')
,(30,104,'TOPIK_UNGGULAN','Tema & Topik Unggulan Poltekkes',2,true,'mdi-table-check','/topik-unggulan')
,(29,104,'PROFIL_DOSEN','Profil Peneliti',8,true,'mdi-view-dashboard','/profil-dosen')
,(9,103,'MENU','Menu',2,true,'mdi-menu','/menu')
,(17,103,'ROLE','Peran',3,true,'mdi-account-cowboy-hat','/role')
,(18,103,'USER_ROLE','Peran per Pengguna',4,true,'mdi-account-child','/user-role')
,(8,103,'USERWEB','Pengguna',1,true,'mdi-account-plus','/userweb')
,(19,103,'ROLE_MENU','Menu per Peran',5,true,'mdi-animation-outline','/role-menu')
,(97,102,'LOG_AKTIFITAS','Log Aktifitas',10,true,'mdi-math-log','/log-aktifitas')
,(31,102,'DOKUMEN_ORGANISASI','Dokumen',3,true,'mdi-file-document-edit','/dokumen-organisasi')
,(79,102,'BERITA','Berita',9,true,'mdi-newspaper-variant-outline','/berita')
,(86,102,'DASHBOARD_NEW','Dashboard',2,true,'mdi-chart-bar','/dashboard')
,(21,101,'TEMA','Tema dan Topik',5,true,'mdi-database','/tema')
,(22,101,'ORGANISASI','Institusi',7,true,'mdi-city','/organisasi')
,(20,101,'SKEMA_PENELITIAN','Skema Penelitian',4,true,'mdi-presentation','/skema-penelitian')
,(28,101,'DOSEN','Peneliti',1,true,'mdi-account','/dosen')
,(68,101,'JENIS_TKT','Kelompok Jenis TKT',5,true,'mdi-laptop','/jenis-tkt')
,(89,88,'LAPORAN_ANGGARAN','Laporan Anggaran',1,true,'mdi-microsoft-excel','/laporan-anggaran')
,(94,79,'BERITA_ADD_UPDATE','Tambah / Ubah Berita',1,false,'','/berita/:id')
,(96,79,'BERITA_DETAIL_FOR_NON_ADMIN','Detail Berita Untuk Non Admin',3,NULL,'','/berita/detail-nonadmin/:id')
,(95,79,'BERITA_DETAIL_FOR_ADMIN','Detail Berita Untuk Admin',2,false,'','/berita/detail/:id')
,(51,50,'IDENTITAS_USULAN','Identitas Usulan',1,NULL,'','/penelitian/:id/identitas-usulan')
,(52,50,'SUBSTANSI_USULAN','Substansi Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
,(54,50,'RAB_USULAN','RAB Usulan',3,NULL,'','/penelitian/:id/rab')
,(70,50,'LUARAN_USULAN','Luaran Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
,(71,50,'SUBSTANSI_LUARAN_USULAN','Substansi Luaran Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
,(77,50,'RIWAYAT_PERUBAHAN_STATUS','Riwayat Perubahan Status',7,NULL,'','/penelitian/:id/riwayat-perubahan-status')
,(48,36,'USULAN_PENELITIAN','Usulan Baru',2,true,'mdi-book-open','/usulan-baru/penelitian')
,(64,48,'RENCANA_KEGIATAN','Rencana Kegiatan',1,NULL,'','/penelitian/:id/rencana-kegiatan')
,(69,48,'MITRA_PENELITIAN','Mitra Penelitian',5,NULL,'','/penelitian/:id/mitra-penelitian')
,(72,48,'KIRIM_USULAN','Kirim Usulan',6,NULL,'','/penelitian/:id/kirim-usulan')
,(37,36,'DAFTAR_RIWAYAT_USULAN','Riwayat Usulan',1,true,'mdi-view-list','/usulan')
,(78,37,'VIEW_USULAN','View Usulan',1,NULL,'','/penelitian/:id/view-usulan')
,(93,37,'DAFTAR_USULAN','Daftar Usulan',1,NULL,'','/usulan')
,(92,37,'RIWAYAT_USULAN','Riwayat Usulan',2,NULL,'','/riwayat-usulan')
,(34,33,'PENUGASAN_REVIEWER','Penugasan',3,true,'mdi-text-box-plus-outline','/penugasan-reviewer')
,(40,33,'PLOTTING_REVIEWER','Plotting',4,true,'mdi-table-account','/plotting-reviewer')
,(42,31,'DOKUMEN_ORGANISASI_ADD','Dokumen Organisasi Tambah',1,NULL,'','/dokumen-organisasi-add')
,(43,31,'DOKUMEN_ORGANISASI_UPDATE','Dokumen Organisasi Ubah',2,NULL,'','/dokumen-organisasi-update')
,(55,29,'JURNAL','Jurnal',1,NULL,'','/jurnal-dosen')
,(56,29,'TAMBAH_UBAH_JURNAL','Tambah/Ubah Jurnal',2,NULL,'','/jurnal-dosen/:id')
,(57,29,'HKI','HKI',3,NULL,'','/hki-dosen')
,(58,29,'TAMBAH_UBAH_HKI','Tambah Ubah HKI',4,NULL,'','/hki-dosen/:id')
,(59,29,'PROSIDING','Prosiding',5,NULL,'','/prosiding-dosen')
,(60,29,'TAMBAH_UBAH_PROSIDING','Tambah/Ubah Prosiding',6,NULL,'','/prosiding-dosen/:id')
,(62,29,'TAMBAH_UBAH_BUKU','Tambah/Ubah Buku',8,NULL,'','/buku-dosen/:id')
,(63,29,'RIWAYAT_PENELITIAN','Riwayat Penelitian',9,NULL,'','/penelitian-dosen')
,(61,29,'BUKU','Buku',7,NULL,'','/buku-dosen')
,(44,28,'DATA_DOSEN','Data Peneliti',1,NULL,'','/dosen/:id')
,(45,22,'ORGANISASI_ADD_UPDATE','Tambah / Ubah Institusi',1,false,'','/organisasi/:id')
,(66,21,'TOPIK','Topik',1,NULL,'','/topik/:id')
,(65,20,'DETAIL_SKEMA_PENELITIAN','Detail Skema Penelitian',1,NULL,'','/skema-penelitian/:id')
,(85,9,'ValidasiTest','Validasi',2,NULL,'','')
,(3,6,'SAMPLE_CRUD','Sample Crud',3,false,'mdi-table','/crud')
,(4,6,'FILE','File',4,false,'mdi-upload-outline','/file')
,(91,5,'TEST_MENU','test menu',2,true,'','')
,(1,5,'DASHBOARD','Dashboard Old',1,false,'mdi-view-dashboard','/dashboard-old')
;

INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(3721,8,5)
,(3722,8,79)
,(3723,8,101)
,(3724,8,44)
,(3725,8,95)
,(3726,8,31)
,(3727,8,42)
,(3728,8,43)
,(3729,8,102)
,(3730,8,28)
,(3731,8,104)
,(49,5,5)
,(50,5,1)
,(51,5,2)
,(52,5,20)
,(3732,8,2)
,(2688,4,5)
,(2689,4,4)
,(2690,4,2)
,(2691,4,3)
,(2692,4,1)
,(3733,8,16)
,(3734,8,94)
,(3735,8,30)
,(3736,8,26)
,(3660,7,5)
,(3661,7,79)
,(3662,7,86)
,(3663,7,101)
,(3664,7,27)
,(3665,7,44)
,(3666,7,96)
,(3667,7,31)
,(3668,7,42)
,(3669,7,43)
,(3670,7,51)
,(3671,7,102)
,(3672,7,88)
,(3673,7,89)
,(3674,7,69)
,(3675,7,28)
,(3676,7,36)
,(3677,7,34)
,(3678,7,40)
,(3679,7,33)
,(3680,7,104)
,(3681,7,2)
,(3682,7,16)
,(3683,7,54)
,(3684,7,39)
,(3685,7,64)
,(3686,7,77)
,(3687,7,81)
,(3688,7,71)
,(3689,7,52)
,(3690,7,26)
,(3691,7,49)
,(3692,7,78)
,(3693,7,30)
,(3851,28,79)
,(3852,28,87)
,(3853,28,96)
,(3854,28,102)
,(3855,28,104)
,(3856,28,2)
,(3857,28,105)
,(3858,28,81)
,(3859,28,26)
,(3860,28,78)
,(3899,9,5)
,(3900,9,79)
,(3901,9,86)
,(3902,9,101)
,(3903,9,27)
,(3904,9,44)
,(3905,9,95)
,(3906,9,65)
,(3907,9,50)
,(3908,9,31)
,(3647,26,103)
,(3648,26,79)
,(3649,26,95)
,(3650,26,96)
,(3651,26,102)
,(3652,26,8)
,(3653,26,18)
,(3654,26,2)
,(3655,26,94)
,(3909,9,42)
,(3910,9,43)
,(3911,9,51)
,(3912,9,22)
,(3913,9,68)
,(3914,9,88)
,(3915,9,89)
,(3916,9,69)
,(3917,9,28)
,(3918,9,34)
,(3919,9,40)
,(3920,9,33)
,(3921,9,2)
,(3922,9,54)
,(3923,9,39)
,(3924,9,64)
,(3925,9,77)
,(3926,9,81)
,(3927,9,20)
,(3928,9,71)
,(3656,26,26)
,(3657,26,31)
,(3658,26,42)
,(3659,26,43)
,(3694,1,103)
,(3695,1,102)
,(3696,1,97)
,(3697,1,9)
,(3698,1,19)
,(3929,9,52)
,(3806,24,5)
,(3930,9,94)
,(3931,9,45)
,(3932,9,21)
,(3933,9,66)
,(3934,9,26)
,(3699,1,17)
,(3700,1,2)
,(3701,1,26)
,(3702,1,79)
,(3703,1,96)
,(3807,24,79)
,(3808,24,61)
,(3809,24,93)
,(3810,24,96)
,(3811,24,65)
,(3812,24,50)
,(3813,24,57)
,(3814,24,51)
,(3815,24,102)
,(3816,24,55)
,(3817,24,72)
,(3818,24,70)
,(3819,24,69)
,(3820,24,36)
,(3821,24,104)
,(3822,24,29)
,(3823,24,2)
,(3824,24,59)
,(3825,24,54)
,(3826,24,64)
,(3827,24,63)
,(3828,24,77)
,(3829,24,37)
,(3830,24,92)
,(3831,24,71)
,(3832,24,52)
,(3833,24,58)
,(3834,24,62)
,(3835,24,56)
,(3836,24,60)
,(3837,24,26)
,(3838,24,48)
,(3839,24,78)
,(3935,9,49)
,(3936,9,78)
,(3937,9,102)
,(10,16,5)
,(11,16,1)
,(12,16,2)
,(13,16,26)
;

INSERT INTO public.kategori_skema_penelitian (id,nama) VALUES 
(1,'Desentralisasi')
,(2,'Penugasan')
;

INSERT INTO public.skema_penelitian (id,id_jenis_penelitian,nama,singkatan,deskripsi,klasifikasi_1,klasifikasi_2,klasifikasi_3,persyaratan_penelitian,tkt_min,tkt_max,jumlah_pengusul_min,jumlah_pengusul_max,jangka_waktu_min,jangka_waktu_max,jumlah_dana_min,jumlah_dana_max,id_kategori_skema_penelitian,is_dikelola_pusat) VALUES 
(4,NULL,'Skema Penelitian Pemula','PP','kema Penelitian Pemula (PP) dimaksudkan sebagai kegiatan penelitian dalam
rangka membina dan mengarahkan para peneliti pemula untuk meningkatkan
kemampuan dalam melaksanakan penelitian dan mempublikasikan hasil penelitiannya
dalam jurnal ilmiah baik nasional maupun internasional.
Skema ini juga memfasilitasi para calon dosen dan tenaga kependidikan untuk
memperoleh kredit point publikasi hasil penelitian yang diperlukan untuk perolehan
jabatan fungsional. Selain untuk membina kemampuan meneliti, skema ini juga
diharapkan dapat menjadi sarana latihan bagi dosen, calon dosen dan tenaga
kependidikan untuk mempublikasikan hasil penelitiannya dalam jurnal ilmiah baik lokal
maupun nasional atau, prosiding nasional, dan untuk memperkaya bahan ajar. Hasil
penelitian skema ini berada di level TKT 1 sampai 3.',true,true,true,'Pengusul berpendidikan S2 dengan maksimal jabatan fungsional asisten ahli atau
belum memiliki jabatan fungsional;
Pengusul dari tenaga kependidikan berpendidikan minimal S1;
Anggota pengusul 1-2 orang; dan
Pengusul hanya boleh mendapatkan skema PP sebanyak dua kali berturut-turut
sebagai ketua atau anggota ditahun yang sama.',1,6,1,2,1,1,10000000,20000000,1,false)
,(6,NULL,'Skema Konsorsium Riset Unggulan Perguruan Tinggi','KRUPT','Penelitian ini dapat dipetakan keunggulan bidang penelitian setiap Poltekkes

Kemenkes. Berdasarkan hasil analisis kekuatan yang telah teridentifikasi pada masing-
masing Poltekkes Kemenkes, selanjutnya dapat diberikan penugasan pelaksanaan

penelitian sesuai keunggulan bidang penelitian Poltekkes Kemenkes dalam bentuk
konsorsium. Selanjutnya pendanaan penelitian ini disebut sebagai Skema Konsorsium Riset
Unggulan Perguruan Tinggi (KRU-PT).
Skema KRU-PT ini merupakan salah satu bentuk program pendanaan penelitian
yang diarahkan untuk peningkatan produktifitas publikasi dan KI serta
komersialisasi/hilirisasi produk hasil penelitian.',true,true,NULL,'a. Pengelola Penelitian Tingkat Pusat menetapkan Poltekkes Kemenkes yang memenuhi
syarat untuk diundang mengikuti seleksi pada suatu bidang unggulan;
b. KRU-PT diketuai oleh Poltekkes Kemenkes yang berdasarkan hasil penilaian Pusat
Unggulan IPTEK Poltekkes Kemenkes;
c. Ketua pengusul berpendidikan S2 dengan jabatan fungsional minimal lector kepala atau
brpendidikan S3 dengan jabatan fungsional minimal Lektor ;
d. Ketua pengusul memiliki rekam jejak publikasi minimal lima artikel di database
terindeks bereputasi dibuktikan dengan memperlihatkan URL artikel dimaksud, atau
minimal memiliki satu KIstatus granted;',4,9,3,10,2,3,250000000,250000000,2,true)
,(2,NULL,'Skema Penelitian Terapan Unggulan Perguruan Tinggi','PTUPT','Penelitian Terapan Unggulan Perguruan Tinggi (PTUPT)merupakan penelitian
yang ditujukan untuk mendapatkan solusi dari suatu masalah yang ada di masyarakat,
industri, pemerintahan sebagai kelanjutan dari riset dasar. Penelitian Terapan Unggulan
Perguruan Tinggi adalah model penelitian yang lebih diarahkan untuk menciptakan
inovasi dan pengembangan ipteks. Penelitian ini berorientasi produk ipteks yang telah
tervalidasi di lingkungan laboratorium/lapangan atau lingkungan yang relevan. Penelitian
terapan Unggulan Perguruan Tinggi merupakan Penelitian yang ditujukan untuk
mendapatkan solusi atas permasalahan tertentu atau sesuai dengan pengukuran tingkat
kesiapterapan teknologi pada tingkat 4 (empat) sampai dengan tingkat 6 (enam). Skema
Penelitian Terapan Unggulan Perguruan Tinggi ini dapat dilakukan untuk penelitian
kerjasama dari dalam atau luar negeri. Penelitian kerjasama luar negeri dapat dilakukan
secara multilateral atau dalam bentuk konsorsium.',true,true,NULL,'a. Pengusul berpendidikan S3 dengan jabatan fungsional minimal asisten ahli, atau
berpendidikan S2 dengan jabatan fungsional minimal lektor;
b. Ketua pengusul memiliki rekam jejak publikasi minimal dua artikel di database
terindeks bereputasi dan/atau jurnal nasional terakreditasi dengan mencantumkan
URL artikel dimaksud, atau minimal memiliki satu KI status terdaftar;
c. Memiliki mitra yang dibuktikan dengan surat pernyataan (dukungan) yang
berisikan kesediaan bekerjasama dalam penelitian. Adanya dukungan pendanaan oleh
mitra menjadi nilai tambah dari usulan; ',4,6,1,2,2,3,160000000,250000000,1,false)
,(3,NULL,'Skema Penelitian Pengembangan Unggulan Perguruan Tinggi','PPUPT','Penelitian Pengembangan Unggulan Perguruan Tinggi (PPUPT) ditujukan untuk
mencapai pengembangan lebih lanjut pada tahapan model/produk/purwarupa yang telah
di uji coba dalam lingkungan yang sebenarnya. Penelitian pengembangan Unggulan
Perguruan Tinggi adalah model penelitian yang lebih diarahkan untuk mengembangkan
produk komersial. Pengembangan merupakan kegiatan untuk peningkatkan kemanfaatan
dan daya dukung ilmu pengetahuan dan teknologi yang telah terbukti kebenaran dan
keamanannya untuk meningkatkan fungsi dan manfaat ilmu pengetahuan dan teknologi
atau sesuai dengan pengukuran tingkat kesiapterapan teknologi pada tingkat 7 (tujuh)
sampai dengan tingkat 9 (sembilan). Dalam penelitian ini diperlukan keterlibatan mitra
sebagai investor Dalam proses pengukuran TKT, hasil Penelitian Pengembangan
Unggulan Perguruan Tinggi berada di level TKT 7 sampai 9.
Skema Penelitian Pengembangan Unggulan Perguruan Tinggi dapat dilakukan untuk
penelitian kerjasama dari dalam atau luar negeri. Penelitian kerjasama luar negeri dapat
dilakukan secara multilateral atau dalam bentuk konsorsium.',true,true,NULL,'a. Pengusul berpendidikan S3 dengan jabatan fungsional minimal asisten ahli, atau
berpendidikan S2 dengan jabatan fungsional minimal lektor;
b. Ketua pengusul memiliki rekam jejak publikasi minimal lima artikel di database
terindeks bereputasi sebagai penulis pertama atau corresponding author dibuktikan
dengan mencantumkan URL artikel dimaksud, atau minimal memiliki satu KI status
granted;
c. Memiliki mitra investor yang dibuktikan dengan surat pernyataan yang berisikan
komitmen penyertaan dana oleh mitra dalam bentuk in cash minimal sebesar 10%
dari dana yang diajukan;',7,9,1,2,3,3,250000000,500000000,1,false)
,(5,NULL,'Skema Penelitian Kerja Sama Antar Perguruan Tinggi','PKPT','Saat ini, hampir di semua kabupaten kota sudah berdiri institusi pendidikan tinggi.
Kondisi ini sangat menggembirakan karena pendidikan tinggi akan lebih mudah dijangkau
oleh masyarakat. Namun demikian, terdapat kesenjangan kualitas penyelenggaraan
tridarma pendidikan tinggi.

PEDOMAN PENELITIAN TAHUN 2020 34
Badan Pengembangan dan Pemberdayaan SDM Kesehatan (BPPSDM) Kemenkes RI
telah melaksanakan berbagai program pembinaan untuk meningkatkan kualitas
penelitian. Hasilnya dapat dilihat dengan telah berkembangnya pusat-pusat penelitian
maupun kelompok-kelompok peneliti unggulan di berbagai Poltekkes Kemenkes di
Indonesia. Kelompok peneliti, laboratorium, dan pusat penelitian tersebut telah memiliki
kemampuan dan suasana akademik yang kondusif untuk pengembangan dan pelaksanaan
penelitian secara baik.
Penelitian ini memfasilitasi kerja sama penelitian antar kelompok peneliti yang
relatif baru berkembang melalui skema Penelitian Kerjasama Antar Perguruan Tinggi

(PKPT). TPP (Tim Peneliti Pengusul) bermitra dengan perguruan tinggi maupun kelompok-
kelompok peneliti mitra (Tim Peneliti Mitra/TPM). Diharapkan melalui kerja sama ini

kualitas penelitian TPP dapat lebih ditingkatkan. PKPT dapat berupa penelitian dasar atau
penelitian terapan Unggulan Perguruan Tinggi dengan TKT 1-6.',true,true,true,'a. TPP terdiri atas ketua dan maksimum dua orang anggota dari Poltekkes Kemenkes;
b. Ketua TPP berpendidikan S-2 dengan jabatan fungsional maksimum lektor; tenaga
kependidikan berpendidikan minimal S1

PEDOMAN PENELITIAN TAHUN 2020 35

c. TPM bukan merupakan tempat TPP menempuh pendidikan terakhir;
d. Klaster kinerja penelitian perguruan tinggi TPM harus lebih tinggi dari perguruan
tinggi TPP;
e. TPP dan TPM harus berasal dari Perguruan Tinggi yang berbeda;
f. TPM terdiri atas satu orang anggota;
g. Peneliti TPM minimal mempunyai 3 publikasi;
h. Usulan penelitian dibuat secara bersama antara TPP dan TPM;
i. Usulan TPP harus mendapat persetujuan TPM melalui Pengelola Penelitian.',1,6,1,3,1,1,20000000,30000000,1,false)
,(9,NULL,'Skema Penelitian Dasar Unggulan Perguruan Tinggi','PDUPT','Penelitian Dasar Unggulan Perguruan Tinggi (PDUPT) merupakan penelitian
yang diperuntukkan bagi pengembangan suatu ilmu pengetahuan dan teknologi untuk
pengembangan teori yang ada dan/atau menemukan teori baru atau sesuai dengan
pengukuran tingkat kesiapterapan teknologi pada tingkat 1 (satu) sampai dengan tingkat 3
(tiga). Berdasarkan Permenristekdikti Nomor 20 Tahun 2018, Penelitian dasar Unggulan
Perguruan Tinggi dikategorikan pada penelitian yang menghasilkan prinsip dasar dari

teknologi, formulasi konsep dan/atau aplikasi teknologi, hingga pembuktian konsep (proof-
of-concept) fungsi dan/atau karakteristik penting secara analitis dan eksperimental.

Sasaran dari penelitian ini adalah dihasilkannya teori, metode, atau prinsip kebijakan baru
yang digunakan untuk pengembangan keilmuan. Penelitian dasar Unggulan Perguruan
Tinggi dapat berorientasi kepada penjelasan atau penemuan (invensi) guna
mengantisipasi suatu gejala/fenomena, kaidah, model, atau postulat baru yang
mendukung suatu proses teknologi, kesehatan,dan lain - lain dalam rangka mendukung
penelitian terapan Unggulan Perguruan Tinggi. Skema Penelitian dasar Unggulan
Perguruan Tinggi ini dapat dilakukan untuk penelitian kerjasama dari dalam atau luar
negeri. Penelitian kerjasama luar negeri dapat dilakukan secara multilateral atau dalam
bentuk konsorsium.',true,true,NULL,'a. Pendidikan S3 dengan minimal jabatan fungsional asisten ahli, atau
berpendidikan S2 dengan jabatan fungsional sekurang-kurangnya lektor;
b. Ketua pengusul memiliki rekam jejak publikasi minimal dua artikel di database
terindeks bereputasi dan/atau jurnal nasional terakreditasi dengan
mencantumkan URL artikel dimaksud; ',1,3,2,3,2,3,1000000000,1500000000,1,false)
,(1,NULL,'Skema Penelitian Dasar Unggulan Perguruan Tinggi','PDUPT','Penelitian Dasar Unggulan Perguruan Tinggi (PDUPT) merupakan penelitian
yang diperuntukkan bagi pengembangan suatu ilmu pengetahuan dan teknologi untuk
pengembangan teori yang ada dan/atau menemukan teori baru atau sesuai dengan
pengukuran tingkat kesiapterapan teknologi pada tingkat 1 (satu) sampai dengan tingkat 3
(tiga). Berdasarkan Permenristekdikti Nomor 20 Tahun 2018, Penelitian dasar Unggulan
Perguruan Tinggi dikategorikan pada penelitian yang menghasilkan prinsip dasar dari

teknologi, formulasi konsep dan/atau aplikasi teknologi, hingga pembuktian konsep (proof-
of-concept) fungsi dan/atau karakteristik penting secara analitis dan eksperimental.

Sasaran dari penelitian ini adalah dihasilkannya teori, metode, atau prinsip kebijakan baru
yang digunakan untuk pengembangan keilmuan. Penelitian dasar Unggulan Perguruan
Tinggi dapat berorientasi kepada penjelasan atau penemuan (invensi) guna
mengantisipasi suatu gejala/fenomena, kaidah, model, atau postulat baru yang
mendukung suatu proses teknologi, kesehatan,dan lain - lain dalam rangka mendukung
penelitian terapan Unggulan Perguruan Tinggi. Skema Penelitian dasar Unggulan
Perguruan Tinggi ini dapat dilakukan untuk penelitian kerjasama dari dalam atau luar
negeri. Penelitian kerjasama luar negeri dapat dilakukan secara multilateral atau dalam
bentuk konsorsium.',true,true,NULL,'a. Pendidikan S3 dengan minimal jabatan fungsional asisten ahli, atau
berpendidikan S2 dengan jabatan fungsional sekurang-kurangnya lektor;
b. Ketua pengusul memiliki rekam jejak publikasi minimal dua artikel di database
terindeks bereputasi dan/atau jurnal nasional terakreditasi dengan
mencantumkan URL artikel dimaksud; ',1,3,2,3,2,3,50000000,1000000000,1,NULL)
,(8,NULL,'Skema Penelitian Dasar Unggulan Perguruan Tinggi 123','PDUPT','Penelitian Dasar Unggulan Perguruan Tinggi (PDUPT) merupakan penelitian
yang diperuntukkan bagi pengembangan suatu ilmu pengetahuan dan teknologi untuk
pengembangan teori yang ada dan/atau menemukan teori baru atau sesuai dengan
pengukuran tingkat kesiapterapan teknologi pada tingkat 1 (satu) sampai dengan tingkat 3
(tiga). Berdasarkan Permenristekdikti Nomor 20 Tahun 2018, Penelitian dasar Unggulan
Perguruan Tinggi dikategorikan pada penelitian yang menghasilkan prinsip dasar dari

teknologi, formulasi konsep dan/atau aplikasi teknologi, hingga pembuktian konsep (proof-
of-concept) fungsi dan/atau karakteristik penting secara analitis dan eksperimental.

Sasaran dari penelitian ini adalah dihasilkannya teori, metode, atau prinsip kebijakan baru
yang digunakan untuk pengembangan keilmuan. Penelitian dasar Unggulan Perguruan
Tinggi dapat berorientasi kepada penjelasan atau penemuan (invensi) guna
mengantisipasi suatu gejala/fenomena, kaidah, model, atau postulat baru yang
mendukung suatu proses teknologi, kesehatan,dan lain - lain dalam rangka mendukung
penelitian terapan Unggulan Perguruan Tinggi. Skema Penelitian dasar Unggulan
Perguruan Tinggi ini dapat dilakukan untuk penelitian kerjasama dari dalam atau luar
negeri. Penelitian kerjasama luar negeri dapat dilakukan secara multilateral atau dalam
bentuk konsorsium.',true,true,NULL,'a. Pendidikan S3 dengan minimal jabatan fungsional asisten ahli, atau
berpendidikan S2 dengan jabatan fungsional sekurang-kurangnya lektor;
b. Ketua pengusul memiliki rekam jejak publikasi minimal dua artikel di database
terindeks bereputasi dan/atau jurnal nasional terakreditasi dengan
mencantumkan URL artikel dimaksud; ',1,3,1,2,2,3,15000000,70000000,1,NULL)
,(7,NULL,'Skema Kajian Kebijakan Strategis','KKS','Proses pengambilan kebijakan dan pelaksanaan di Kementerian Kesehatan
memerlukan dukungan kajian kebijakan. Hal ini dimaksudkan agar kebijakan yang diambil
dapat secara efektif menjawab permasalahan yang ada dalam dinamika lingkungan strategis
yang dihadapi baik internal maupun eksternal. Dengan latar belakang tersebut, Badan
PPSDM Kesehatan memfasilitasi melalui skema Kajian Kebijakan Strategis (KKS). Skema ini
diharapkan dapat memberikan landasan akademik yang kuat dalam penetapan
kebijakan strategis.
Kajian kebijakan strategis dapat berupa telaah terhadap kebijakan yang dijalankan
atau telaah terhadap kebijakan yang akan diambil. Telaah terhadap kebijakan yang sudah
ada dan/atau yang sudah dijalankan diharapkan dapat memberikan landasan ilmiah yang
kuat untuk dilakukannya perbaikan yang diperlukan. Kajian terhadap kebijakan yang akan
diambil diharapkan dapat memberikan landasan ilmiah yang kuat dalam proses, konteks,
dan substansi kebijakan.',true,true,true,'Ketua pengusul berpendidikan S3 sesuai keahlian dalam rumpun ilmu bidang strategis
yang ditugaskan dengan jabatan minimal Lektor ;
b. Ketua pengusul memiliki rekam jejak publikasi berupa artikel di database terindeks
bereputasi sekurang-kurangnya dua artikel;',4,9,2,5,1,4,4000000,6000000,2,true)
;

INSERT INTO public.tahap_penelitian (id,nama) VALUES 
(1,'Pengusulan')
,(2,'Review/Penelaahan')
,(3,'Pelaksanaan')
,(4,'Pelaporan')
;

INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(1, NULL, 1, 'Draft, proposal belum disubmit', NULL, 'DRAFT', 'DOSEN', NULL, 'Buat Proposal Baru');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(2, 1, 1, 'Proposal sudah disubmit, menunggu plotting reviewer', NULL, 'SUBMITTED', 'DOSEN', NULL, 'Kirim Proposal');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(3, 2, 2, 'Proposal sudah diplot, menunggu hasil review', NULL, 'PLOTTED', 'PENGELOLA_PUSAT', 'KAPUS_PPM', 'Plot Reviewer');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(10, 3, 2, 'Proposal sedang direview', NULL, 'IN_REVIEW', 'REVIEWER', NULL, 'Mulai Review');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(5, 10, 2, 'Sudah direview, proposal perlu perbaikan', NULL, 'NEED_REVISION', 'REVIEWER', NULL, 'Kembalikan Proposal');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(6, 5, 2, 'Draft sedang diperbaiki, belum disubmit', NULL, 'DRAFT_REVISION', 'DOSEN', NULL, 'Perbaiki Proposal');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(7, 6, 2, 'Perbaikan sudah disubmit, menunggu hasil review 2', NULL, 'REVISED', 'DOSEN', NULL, 'Kirim Perbaikan');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(11, 7, 2, 'Revisi proposal sedang direview', NULL, 'REVISION_IN_REVIEW', 'REVIEWER', NULL, 'Mulai Review Revisi');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(8, 10, 2, 'Sudah direview, proposal ditolak', 11, 'REJECTED', 'REVIEWER', NULL, 'Tolak Proposal');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(9, 1, 2, 'Draft tertolak, karena habis waktu pengusulan', 6, 'DEADLINE_PASSED', 'SYSTEM', NULL, 'Lewat Deadline');
INSERT INTO public.status_tahap_penelitian
(id, id_status_syarat_1, id_tahap_penelitian, nama, id_status_syarat_2, kode_status, kode_role_1, kode_role_2, label_button)
VALUES(4, 10, 2, 'Sudah direview, proposal diterima', 11, 'ACCEPTED', 'REVIEWER', NULL, 'Terima Proposal');

-- ** UPDATE SEQUENCE ** --
SELECT setval('kelompok_tkt_id_seq', (SELECT MAX(id) FROM kelompok_tkt));
SELECT setval('tema_id_seq', (SELECT MAX(id) FROM tema));
SELECT setval('topik_id_seq', (SELECT MAX(id) FROM topik));
SELECT setval('organisasi_id_seq', (SELECT MAX(id) FROM organisasi));
SELECT setval('userweb_id_seq', (SELECT MAX(id) FROM userweb ));
SELECT setval('role_id_seq', (SELECT MAX(id) FROM "role" ));
SELECT setval('menu_id_seq', (SELECT MAX(id) FROM menu ));
SELECT setval('userweb_has_role_id_seq', (SELECT MAX(id) FROM userweb_has_role ));
SELECT setval('role_has_menu_id_seq', (SELECT MAX(id) FROM role_has_menu ));
SELECT setval('kategori_skema_penelitian_id_seq', (SELECT MAX(id) FROM kategori_skema_penelitian ));
SELECT setval('skema_penelitian_id_seq', (SELECT MAX(id) FROM skema_penelitian ));
SELECT setval('tahap_penelitian_id_seq', (SELECT MAX(id) FROM skema_penelitian ));
SELECT setval('status_tahap_penelitian_id_seq', (SELECT MAX(id) FROM skema_penelitian ));

