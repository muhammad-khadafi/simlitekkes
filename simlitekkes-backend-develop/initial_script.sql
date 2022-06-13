-- public.bagian_usulan definition

-- Drop table

DROP TABLE IF EXISTS public.bagian_usulan CASCADE;

CREATE TABLE public.bagian_usulan (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT bagian_usulan_pkey PRIMARY KEY (id)
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
	id int4 NULL,
	nama varchar NULL,
	nilai varchar NULL
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
	CONSTRAINT menu_pkey PRIMARY KEY (id)
);


-- public.peran_peneliti definition

-- Drop table

DROP TABLE IF EXISTS public.peran_peneliti CASCADE;

CREATE TABLE public.peran_peneliti (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT peran_peneliti_pkey PRIMARY KEY (id)
);


-- public.peran_penulis_output definition

-- Drop table

DROP TABLE IF EXISTS public.peran_penulis_output CASCADE;

CREATE TABLE public.peran_penulis_output (
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT peran_penulis_publikasi_pkey PRIMARY KEY (id)
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


-- public.role_has_menu definition

-- Drop table

DROP TABLE IF EXISTS public.role_has_menu CASCADE;

CREATE TABLE public.role_has_menu (
	id bigserial NOT NULL,
	id_role int8 NULL,
	id_menu int8 NULL,
	CONSTRAINT role_has_menu_pkey PRIMARY KEY (id)
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
	id serial NOT NULL,
	nama varchar(100) NULL,
	CONSTRAINT tipe_user_pkey PRIMARY KEY (id)
);


-- public.userweb_has_role definition

-- Drop table

DROP TABLE IF EXISTS public.userweb_has_role CASCADE;

CREATE TABLE public.userweb_has_role (
	id bigserial NOT NULL,
	id_userweb int8 NULL,
	id_role int8 NULL,
	CONSTRAINT userweb_has_role_pkey PRIMARY KEY (id)
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
	deskripsi varchar(250) NULL,
	CONSTRAINT indikator_tkt_pkey PRIMARY KEY (id),
	CONSTRAINT indikator_tkt_id_tingkat_kesiapan_teknologi_fkey FOREIGN KEY (id_tingkat_kesiapan_teknologi) REFERENCES tingkat_kesiapan_teknologi(id)
);


-- public.komponen_penilaian definition

-- Drop table

DROP TABLE IF EXISTS public.komponen_penilaian CASCADE;

CREATE TABLE public.komponen_penilaian (
	id serial NOT NULL,
	nama varchar(100) NULL,
	id_skema_penelitian int4 NULL,
	id_tahap_penelitian int4 NULL,
	CONSTRAINT komponen_penilaian_pkey PRIMARY KEY (id),
	CONSTRAINT komponen_penilaian_id_skema_penelitian_fkey FOREIGN KEY (id_skema_penelitian) REFERENCES skema_penelitian(id),
	CONSTRAINT komponen_penilaian_id_tahap_penelitian_fkey FOREIGN KEY (id_tahap_penelitian) REFERENCES tahap_penelitian(id)
);


-- public.kriteria_penilaian definition

-- Drop table

DROP TABLE IF EXISTS public.kriteria_penilaian CASCADE;

CREATE TABLE public.kriteria_penilaian (
	id serial NOT NULL,
	deskripsi varchar(250) NULL,
	id_komponen_penilaian int4 NULL,
	skor_maksimal int4 NULL,
	CONSTRAINT kriteria_penilaian_pkey PRIMARY KEY (id),
	CONSTRAINT kriteria_penilaian_id_komponen_penillaian_fkey FOREIGN KEY (id_komponen_penilaian) REFERENCES komponen_penilaian(id)
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
	is_optional bool NULL,
	is_additional bool NULL,
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
	nilai int4 NOT NULL,
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
	last_ip varchar(25) NULL,
	foto varchar(100) NULL,
	nip varchar(25) NULL,
	nama varchar(100) NULL,
	email varchar(100) NULL,
	is_active bool NULL,
	id_organisasi int4 NULL,
	foto_name varchar(100) NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id),
	CONSTRAINT userweb_organisasi_fk FOREIGN KEY (id_organisasi) REFERENCES organisasi(id)
);


-- public.berita definition

-- Drop table

DROP TABLE IF EXISTS public.berita CASCADE;

CREATE TABLE public.berita (
	id serial NOT NULL,
	id_organisasi int4 NULL,
	judul varchar(250) NULL,
	isi text NULL,
	is_published bool NULL,
	user_publish varchar(100) NULL,
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
	id_skema_penelitian int4 NULL,
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
	CONSTRAINT reviewer_skema_id_peneliti_fkey FOREIGN KEY (id_peneliti) REFERENCES peneliti(id),
	CONSTRAINT reviewer_skema_id_skema_penelitian_fkey FOREIGN KEY (id_skema_penelitian) REFERENCES skema_penelitian(id)
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
	tahun_realisasi varchar(4) NOT NULL,
	is_wajib bool NOT NULL DEFAULT false,
	is_tambahan bool NOT NULL DEFAULT false,
	id_jenis_output int4 NULL,
	CONSTRAINT checklist_output_penelitian_pkey PRIMARY KEY (id),
	CONSTRAINT checklist_output_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
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
	CONSTRAINT workflow_penelitian_id_penelitian UNIQUE (id_penelitian, id_status_tahap_penelitian),
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


-- public.komentar definition

-- Drop table

DROP TABLE IF EXISTS public.komentar CASCADE;

CREATE TABLE public.komentar (
	id serial NOT NULL,
	id_penelitian int4 NULL,
	id_bagian_usulan int4 NULL,
	komentar text NULL,
	timestamp_update timestamp NULL,
	id_user int4 NULL,
	CONSTRAINT komentar_pkey PRIMARY KEY (id),
	CONSTRAINT komentar_id_bagian_usulan_fkey FOREIGN KEY (id_bagian_usulan) REFERENCES bagian_usulan(id),
	CONSTRAINT komentar_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
);


-- public.kriteria_penilaian_penelitian definition

-- Drop table

DROP TABLE IF EXISTS public.kriteria_penilaian_penelitian CASCADE;

CREATE TABLE public.kriteria_penilaian_penelitian (
	id_penelitian int4 NULL,
	id_kriteria_penilaian int4 NULL,
	id_pilihan_kriteria_penilaian int4 NOT NULL,
	komentar varchar(250) NOT NULL,
	CONSTRAINT kriteria_penilaian_penelitian_id_penelitian_id_kriteria_penilai UNIQUE (id_penelitian, id_kriteria_penilaian),
	CONSTRAINT kriteria_penilaian_penelitian_id_kriteria_penilaian_fkey FOREIGN KEY (id_kriteria_penilaian) REFERENCES kriteria_penilaian(id),
	CONSTRAINT kriteria_penilaian_penelitian_id_penelitian_fkey FOREIGN KEY (id_penelitian) REFERENCES penelitian(id)
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
	url varchar(100) NULL,
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
	url varchar(100) NULL,
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
	url varchar(100) NULL,
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
	url varchar(100) NULL,
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
(1,'TKT Jenis Kesehatan – Produk Vaksin/Hayati',NULL,NULL)
,(2,'TKT Jenis Kesehatan – Produk Alat Kesehatan',NULL,NULL)
,(4,'TKT Jenis Sosial Humaniora dan Pendidikan',NULL,NULL)
,(3,'TKT Jenis Farmasi',true,'2019')
,(10,'TKT - Test ke 1',true,'2019')
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
;

INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(1, 3, 'PPSDM', '', 'Jl. Hang Jebat III Blok F3, Kebayoran Baru', '0217245517, 72797302', '021-72797302', 'ppsdm@gmail.com', 'Direktur', '02.354.7535.324.00', 'drg. Ita', 'P', '', false, 1, 'http://www.bppsdmk.kemkes.go.id/web/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(2, 3, 'Poltekkes Jakarta 3', NULL, 'Jl. Melati 2 No.15, RT.001/RW.009, Jatiwarna, Kec. Pd. Melati, Kota Bks, Jawa Barat 17415', '', '', 'sekretariat@poltekkesjakarta3.ac.id', NULL, NULL, NULL, 'L', 'S2', true, 1, 'http://www.poltekkesjakarta3.ac.id/jakarta3/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(6, NULL, 'Universitas Indonesia', NULL, 'Depok', '', '', '', NULL, NULL, NULL, NULL, NULL, false, NULL, 'www.ui.ac.id');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(7, 16, 'Poltekkes Jakarta 2', NULL, 'Jl. Hang Jebat', '(021) 7397641', '021-74793082', 'info@poltekkesjkt2.ac.id', 'Direktur', NULL, 'Joko Sulistyo', 'L', 'S3', true, 2, 'http://www.poltekkesjkt2.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(9, NULL, 'Poltekkes Semarang', NULL, 'Jalan Tirto Agung Pedalangan, Banyumanik, Semarang 50268', '', '', '', NULL, NULL, NULL, NULL, NULL, true, NULL, 'https://poltekkes-smg.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(10, NULL, 'Poltekkes Yogyakarta', NULL, 'Jl. Tatabumi No. 3, Banyuraden, Gamping, Sleman', '', '', '', NULL, NULL, NULL, NULL, NULL, true, NULL, 'https://poltekkesjogja.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(11, NULL, 'Poltekes Banjarmasin', NULL, 'Jl. Mistar Cokrokusumo No.1A Banjarbaru 70714', '', '', '', NULL, NULL, NULL, NULL, NULL, true, 3, 'https://poltekkes-banjarmasin.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(12, NULL, 'Poltekkes Makasar', NULL, 'Jl. Wijaya Kusuma No.46, Banta-Bantaeng, Kec. Rappocini, Kota Makassar, Sulawesi Selatan 90222', '', '', '', NULL, NULL, NULL, NULL, NULL, true, NULL, 'http://www.poltekkes-mks.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(13, NULL, 'Poltekkes Bandung', NULL, 'Gedung Direktorat Poltekkes Bandung, Jl. Pajajaran No.56 Bandung ', '', '', 'info@poltekkesbandung.ac.id', NULL, NULL, NULL, NULL, NULL, true, NULL, 'https://poltekkesbandung.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(14, NULL, 'Poltekkes Malang', NULL, 'Jalan Besar Ijen 77C
Malang - JAwa Timur
65112', 'Tel: +62-341-566075', '', '', NULL, NULL, NULL, NULL, NULL, true, NULL, 'http://www.poltekkes-malang.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(15, NULL, 'Poltekkes Surabaya', NULL, 'Jl. Pucang Jajar Tengah 56
Surabaya Jawa Timur', '(031) 5027058', '(031) 5028141', 'admin@poltekkesdepkes-sby.ac.id', NULL, NULL, NULL, NULL, NULL, true, NULL, 'https://poltekkesdepkes-sby.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(17, 6, 'Poltekkes Jakarta 1', NULL, 'Jl. Wijayakusuma Raya No. 47-48 Cilandak - Jakarta Selatan', '02175909605, 7657701', '021-75909638', 'informasi@poltekkesjakarta1.ac.id', NULL, NULL, '', 'L', NULL, true, 1, 'https://www.poltekkesjakarta1.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(18, NULL, 'Universitas Trisakti', NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, false, NULL, '');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(19, NULL, 'Universitas Gunadarma', NULL, 'Jalan Margonda Raya 100, Kota Depok Jawa Barat - 16424', '02178881112 ext. 234', '', 'webmaster@gunadarma.ac.id', NULL, NULL, NULL, NULL, NULL, false, NULL, 'https://www.gunadarma.ac.id/');
INSERT INTO public.organisasi
(id, id_topik, nama, no_sk_pendirian, alamat, no_telp, no_fax, email, nama_jabatan_kepala, nidn_pejabat_kepala, nama_pejabat_kepala, jenis_kelamin, jenjang_pendidikan, is_poltekkes, klasifikasi, website)
VALUES(21, NULL, 'Poltekkes Riau', NULL, 'Jl. Melur 103, Sukajadi, Pekanbaru, Kota Pekanbaru - Prop. Riau - Indonesia', '', '', '', NULL, NULL, NULL, NULL, NULL, true, 3, '');

INSERT INTO public.userweb (username,"password",last_login,last_ip,foto,nip,nama,email,is_active,id_organisasi,foto_name) VALUES 
('robert',NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,NULL,NULL)
,('aslis','$2a$10$OjlN/43ATnxwCUMyY4zhXuagAS1QVPg2zzihujGyZuJez9DALq0Wm',NULL,NULL,NULL,'','Dr. Aslis Wirda Hayati, SP, M.SI','info@pusilkom.com',true,21,'')
,('roy','$2a$10$RigKUEqfGDxXPCtzIol2M.Uyq.QqaKeQy5WYH/hOUXEadYRQJ6ovC',NULL,NULL,'','1230273740','roy albert','roy@gmail.com',true,1,NULL)
,('user123','$2a$10$bIJVcE4V.fcK3ymnB9mrf.Cz595Fa2odI2iOuvrwAgSLhmsYAq5CG',NULL,NULL,NULL,'123','user123','user123@user.com',true,1,NULL)
,('ppsdmkw','$2a$10$piHaGgfacy6yvIVogBs.u.GVXStL89u9XdhBmcbCr7AKzG106BP5O',NULL,NULL,NULL,'','Ppsdm KW','info@pusilkom.com',true,17,'')
,('ira','$2a$10$riLUVbEIbW70A4A1QePt8Os87VlSqC1RwZPUCjXSGWWArUg2TMTCa',NULL,NULL,NULL,'','Ira Oktaviani Rz, S. Farm, Apt, M.Farm','info@pusilkom.com',true,21,'')
,('dosen2','$2a$10$XlhmAJDYjbUyJUSRvGXH7OFdrkKchEtehFsj48kRPerPGnjR5Zluu',NULL,NULL,NULL,'1010101010','dosen2','dosen2@gmail.com',true,2,'')
,('admin','$2a$10$BPI9wg3sT.J826sbLhB.B./owLWCgBv40GRGyJKd.Yv6/mzJcKOiO','2020-02-01 00:00:00.000','192.10.10.771','file\userweb-foto\amazon-20200518111238.png','120735662728365','Sony Tulung','admin@admin.com',true,1,'amazon-20200518111238.png')
,('kapuskw','$2a$10$eXn9gfO0bzH9mnOw1MnmDu9WmXKsbOGSUO365v4Bx7v9jxMqGEsaG',NULL,NULL,NULL,'','Kapus KW','info@pusilkom.com',true,2,'')
,('mike','5f4dcc3b5aa765d61d8327deb882cf99',NULL,NULL,NULL,NULL,'mike','mike@mike.com',true,13,NULL)
;
INSERT INTO public.userweb (username,"password",last_login,last_ip,foto,nip,nama,email,is_active,id_organisasi,foto_name) VALUES 
('SoniaTest','$2a$10$rF7riDaoxSa.VaJgafMsEePCS6tk6BO8eLjXkX7b0ekYX5K5UGZgu',NULL,NULL,'file\userweb-foto\yoo in na-20200527154202.jpg','1234567890987654','Sonia Anya','4ny4.sgd@gmail.com',true,7,'yoo in na-20200527154202.jpg')
,('kapus','$2a$10$ZYix5EPy6S0p8cYyPtg/teyiBCfsrQc5piq4ipOcg8w7y5pZh1IDm',NULL,NULL,'file\userweb-foto\logo-pusilkom-20200408102705-20200515121358.jpg','111111','Alberto','info@pusilkom.com',true,17,'logo-pusilkom-20200408102705-20200515121358.jpg')
,('zahra','$2a$10$Q8qADPZTDb43z0ZZjaQ3OuRMDNsVd8RXnyYrihES/IASi5f9V20le',NULL,NULL,NULL,'01.9009.2111','Fatimah','info@pusilkom.com',true,2,'')
,('operatorppmkw','$2a$10$slXFtAGMSH..vMUsE9tANO0vTwbcGkCChccVn8Rofs1c8sAXehwAK',NULL,NULL,NULL,'','Operator PPM KW','info@pusilkom.com',true,6,'')
,('nunu','$2a$10$BPI9wg3sT.J826sbLhB.B./owLWCgBv40GRGyJKd.Yv6/mzJcKOiO',NULL,NULL,'file\userweb-foto\ampli-20200324093635.jpg','nunu','nunu','dhafidrim@gmail.com',true,2,NULL)
,('testemail','$2a$10$TItw4ilxfFUtVihX9McAMeYlCC3rf1K6HbnZCZU8dpFJHtOoBH.ai',NULL,NULL,NULL,NULL,NULL,'dayaadianto@ui.ac.id',NULL,17,NULL)
,('operatorppm','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6',NULL,NULL,NULL,'123123','Randu','operatorppm@gmail.com',true,17,NULL)
,('dosen','$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6',NULL,NULL,'file\userweb-foto\lee dong wook-20200515130042-20200529162358.jpg','123456','Dosen','d@g.co',true,17,'lee dong wook-20200515130042-20200529162358.jpg')
,('matt','$2a$10$k705jG2DiwZApxcVDz647.GA8Nr4j5cNwqJnwfllfP.bmYE1GN6cy',NULL,NULL,'file\userweb-foto\WhatsApp Image 2020-04-22 at 1.00.50 PM-20200518102026.jpeg',NULL,'mamat','mamat@afdfsf.csd',true,17,'WhatsApp Image 2020-04-22 at 1.00.50 PM-20200518102026.jpeg')
,('Administrator','$2a$10$mq1bhn1BpTTsFnC9NL2ExObPcCZHG7PvxigQlLDGfBx0MIbzUa5Ba',NULL,NULL,'file\userweb-foto\WhatsApp Image 2020-04-22 at 1.00.50 PM-20200518102151.jpeg','','Administrator','sonia@pusilkom.com',true,1,'WhatsApp Image 2020-04-22 at 1.00.50 PM-20200518102151.jpeg')
;
INSERT INTO public.userweb (username,"password",last_login,last_ip,foto,nip,nama,email,is_active,id_organisasi,foto_name) VALUES 
('pakmentri','$2a$10$uwf2lkLGQ3kQI.lqB3i5V.CJFqM2ZjrvuLD5MTZfjQzpwaSl08tgy',NULL,NULL,NULL,NULL,'pak Mentri','taufan.tif@gmail.com',true,NULL,NULL)
,('pengelolapusat','$2a$10$.w5d0uQvqKnT/F.Lbi5g0uelDHsqBFG8r5deE5QxNoWwluHt5EWGK',NULL,NULL,NULL,NULL,'Rangga','pengelolapusat@gmail.com',true,1,NULL)
,('operatorppm2','$2a$10$UVIotZuxGSd5DS6hadVXFewBjvY1mRCeYNG8ROycW8Bbu9vXtxfIq',NULL,NULL,'file\userweb-foto\kemenkes-ri-20200331145308.jpg','12345','Operator PPM 2','operatorppm2@gmail.com',true,7,'kemenkes-ri-20200331145308.jpg')
,('adita','$2a$10$2qIJkPYxqJmFa0LDz.uhLOayR5IAX2VAIU4OGNlQgAVC.1Wi/yVya',NULL,NULL,NULL,'','Adita Ratnawidi','info@pusilkom.com',true,21,'')
,('adminppm','$2a$10$BPI9wg3sT.J826sbLhB.B./owLWCgBv40GRGyJKd.Yv6/mzJcKOiO',NULL,NULL,NULL,'','Admin PPM','adminppm@adminppm.com',true,17,'')
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
;

INSERT INTO public.userweb_has_role (id,id_userweb,id_role) VALUES 
(10,1,1)
,(30,7,8)
,(31,8,9)
,(43,12,8)
,(44,32,24)
,(45,33,8)
,(46,9,7)
,(47,38,3)
,(54,39,8)
,(55,39,9)
,(56,39,24)
,(57,39,7)
,(61,41,26)
,(70,6,5)
,(71,20,1)
,(72,20,8)
,(73,20,9)
,(74,20,7)
;

INSERT INTO public.menu (id,id_parent,menu_code,menu_name,menu_order,is_displayed,icon,router_to) VALUES 
(1,5,'DASHBOARD','Dashboard',1,true,'mdi-view-dashboard','/dashboard')
,(2,5,'USER_PROFILE','User Profile',2,true,'mdi-account','/user-profile')
,(3,6,'SAMPLE_CRUD','Sample Crud',3,true,'mdi-table','/crud')
,(4,6,'FILE','File',4,true,'mdi-upload-outline','/file')
,(5,NULL,'HOME','Home',1,true,'mdi-home',NULL)
,(9,7,'MENU','Menu',2,true,'mdi-menu','/menu')
,(6,NULL,'DATA','Data',2,true,'mdi-cog-box',NULL)
,(17,7,'ROLE','Role',3,true,'mdi-account-cowboy-hat','/role')
,(18,7,'USER_ROLE','User Role',4,true,'mdi-account-child','/user-role')
,(19,7,'ROLE_MENU','Role Menu',5,true,'mdi-animation-outline','/role-menu')
,(48,36,'USULAN_PENELITIAN','Usulan Baru',2,true,'mdi-book-open','/usulan/penelitian')
,(49,39,'USULAN_PER_SKEMA_TAHUN','Usulan per Skema Tahun',1,NULL,'','/penelitian/usulan/:idSkemaPenelitian/:tahunUsulan')
,(50,49,'DETAIL_USULAN','Detail Usulan',1,NULL,'','/penelitian/:id/identitas-usulan')
,(51,50,'IDENTITAS_USULAN','Identitas Usulan',1,NULL,'','/penelitian/:id/identitas-usulan')
,(52,50,'SUBSTANSI_USULAN','Substansi Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
,(27,NULL,'DATA_PENDUKUNG','Data Pendukung',2,true,'mdi-folder-open','')
,(33,NULL,'PENILAIAN_PRA','Penilaian Pra',3,true,'mdi-text-box-check-outline','')
,(34,33,'PENUGASAN_REVIEWER','Penugasan Reviewer',3,true,'mdi-text-box-plus-outline','/penugasan-reviewer')
,(54,50,'RAB_USULAN','RAB Usulan',3,NULL,'','/penelitian/:id/rab')
,(35,33,'PENUGASAN_REVIEWER_EKSTERNAL','Penugasan Reviewer Eksternal',3,true,'mdi-text-box-plus-outline','/penugasan-reviewer-eksternal')
,(28,27,'DOSEN','Manajemen Dosen',1,true,'mdi-account','/dosen')
,(68,5,'JENIS_TKT','Kelompok Jenis TKT',5,true,'mdi-laptop','/jenis-tkt')
,(30,27,'TOPIK_UNGGULAN','Tema & Topik Unggulan Poltekkes',2,true,'mdi-table-check','/topik-unggulan')
,(26,5,'CHANGE_PASSWORD','Ubah Password',3,true,'mdi-account-key','/change-password')
,(36,NULL,'PENELITIAN','Penelitian',2,true,'mdi-square-edit-outline','')
,(37,36,'DAFTAR_USULAN','Daftar Usulan',2,true,'mdi-view-list','/usulan')
,(55,29,'JURNAL','Jurnal',1,NULL,'','/jurnal-dosen')
,(56,29,'TAMBAH_UBAH_JURNAL','Tambah/Ubah Jurnal',2,NULL,'','/jurnal-dosen/:id')
,(57,29,'HKI','HKI',3,NULL,'','/hki-dosen')
,(58,29,'TAMBAH_UBAH_HKI','Tambah Ubah HKI',4,NULL,'','/hki-dosen/:id')
,(59,29,'PROSIDING','Prosiding',5,NULL,'','/prosiding-dosen')
,(60,29,'TAMBAH_UBAH_PROSIDING','Tambah/Ubah Prosiding',6,NULL,'','/prosiding-dosen/:id')
,(61,29,'BUKU','Buku',7,NULL,'','/buku-dosen')
,(62,29,'TAMBAH_UBAH_BUKU','Tambah/Ubah Buku',8,NULL,'','/buku-dosen/:id')
,(63,29,'RIWAYAT_PENELITIAN','Riwayat Penelitian',9,NULL,'','/penelitian-dosen')
,(31,27,'DOKUMEN_ORGANISASI','Dokumen Organisasi',3,true,'mdi-file-document-edit','/dokumen-organisasi')
,(20,5,'SKEMA_PENELITIAN','Skema Penelitian',4,true,'mdi-presentation','/skema-penelitian')
,(21,5,'TEMA','Tema dan Topik',5,true,'mdi-database','/tema')
,(16,5,'PROFIL_POLTEKKES','Profil Poltekkes',6,true,'mdi-bank','/profil-poltekkes')
,(70,50,'LUARAN_USULAN','Luaran Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
,(40,33,'PLOTTING_REVIEWER','Plotting Reviewer',4,true,'mdi-table-account','/plotting-reviewer')
,(41,33,'PLOTTING_REVIEWER_EKSTERNAL','Plotting Reviewer Eksternal',4,true,'mdi-table-account','/plotting-reviewer-eksternal')
,(8,7,'USERWEB','User',1,true,'mdi-account-plus','/userweb')
,(29,5,'PROFIL_DOSEN','Profil Dosen',8,true,'mdi-view-dashboard','/profil-dosen')
,(39,5,'REKAP_USULAN','Rekap Usulan',9,true,'mdi-view-list','/penelitian/rekap')
,(64,48,'RENCANA_KEGIATAN','Rencana Kegiatan',1,NULL,'','/penelitian/:id/rencana-kegiatan')
,(42,31,'DOKUMEN_ORGANISASI_ADD','Dokumen Organisasi Tambah',1,NULL,'','/dokumen-organisasi-add')
,(43,31,'DOKUMEN_ORGANISASI_UPDATE','Dokumen Organisasi Ubah',2,NULL,'','/dokumen-organisasi-update')
,(44,28,'DATA_DOSEN','Data Dosen',1,NULL,'','/dosen/:id')
,(7,NULL,'USER_MANAGEMENT','User Management',3,true,'mdi-account','')
,(46,40,'PLOTTING_REVIEWER_ASSIGNMENT_LIST','Daftar Plotting Reviewer',1,NULL,'','/plotting-reviewer-assignment-list')
,(47,34,'PENUGASAN_REVIEWER_LIST','Daftar Penugasan Reviewer',1,NULL,'','/reviewer-assignment/:idSkema/:idOrganisasi/:tahunUsulan/:tahunPelaksanaan')
,(22,5,'ORGANISASI','Manajemen Institusi',7,true,'mdi-city','/organisasi')
,(45,22,'ORGANISASI_ADD_UPDATE','Tambah / Ubah Institusi',1,false,'','/organisasi/:id')
,(65,20,'DETAIL_SKEMA_PENELITIAN','Detail Skema Penelitian',1,NULL,'','/skema-penelitian/:id')
,(66,21,'TOPIK','Topik',1,NULL,'','/topik/:id')
,(69,48,'MITRA_PENELITIAN','Mitra Penelitian',5,NULL,'','/penelitian/:id/mitra-penelitian')
,(71,50,'SUBSTANSI_LUARAN_USULAN','Substansi Luaran Usulan',2,NULL,'','/penelitian/:id/substansi-usulan')
;

INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(751,8,44)
,(752,8,27)
,(753,8,31)
,(754,8,42)
,(755,8,43)
,(756,8,5)
,(757,8,28)
,(1309,1,61)
,(1310,1,37)
,(269,16,5)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(270,16,1)
,(271,16,2)
,(272,16,26)
,(1311,1,1)
,(24,4,1)
,(25,4,2)
,(26,4,3)
,(27,4,4)
,(28,4,5)
,(1312,1,6)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1313,1,44)
,(1314,1,27)
,(1315,1,65)
,(1316,1,4)
,(1317,1,57)
,(1318,1,5)
,(1319,1,55)
,(1320,1,28)
,(1321,1,22)
,(1322,1,9)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1323,1,36)
,(1324,1,33)
,(1325,1,34)
,(1326,1,35)
,(49,5,5)
,(50,5,1)
,(51,5,2)
,(52,5,20)
,(1327,1,40)
,(1328,1,41)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1329,1,29)
,(1330,1,16)
,(1331,1,59)
,(1332,1,63)
,(1333,1,17)
,(1334,1,19)
,(758,8,16)
,(759,8,30)
,(760,8,26)
,(1335,1,3)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1336,1,20)
,(1337,1,58)
,(1338,1,62)
,(1339,1,56)
,(1340,1,60)
,(1341,1,30)
,(1342,1,21)
,(1343,1,66)
,(1344,1,26)
,(1345,1,8)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1346,1,7)
,(1347,1,2)
,(1348,1,18)
,(1349,1,48)
,(1399,24,61)
,(1400,24,37)
,(1401,24,65)
,(1402,24,50)
,(1403,24,57)
,(1404,24,5)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1405,24,51)
,(1406,24,55)
,(1407,24,70)
,(1408,24,69)
,(1409,24,36)
,(1410,24,29)
,(1411,24,59)
,(1412,24,54)
,(1413,24,64)
,(1414,24,63)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1415,24,52)
,(1416,24,58)
,(1417,24,62)
,(1418,24,56)
,(1419,24,60)
,(1420,24,66)
,(436,26,17)
,(437,26,19)
,(438,26,26)
,(439,26,8)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1421,24,48)
,(1422,24,71)
,(440,26,7)
,(441,26,18)
,(442,26,5)
,(1423,7,47)
,(1424,7,46)
,(1425,7,1)
,(1426,7,27)
,(1427,7,5)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1428,7,51)
,(1429,7,28)
,(1430,7,69)
,(1431,7,36)
,(1432,7,33)
,(1433,7,34)
,(1434,7,40)
,(1435,7,29)
,(1436,7,16)
,(1437,7,54)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1438,7,39)
,(1439,7,64)
,(1440,7,52)
,(1441,7,30)
,(1442,7,26)
,(1443,7,2)
,(1444,7,48)
,(1445,7,49)
,(1446,7,71)
,(1447,9,47)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1448,9,46)
,(1449,9,1)
,(1450,9,44)
,(1451,9,27)
,(1452,9,50)
,(1453,9,5)
,(1454,9,51)
,(1455,9,68)
,(1456,9,28)
,(1457,9,22)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1458,9,69)
,(1459,9,36)
,(1460,9,33)
,(1461,9,35)
,(1462,9,41)
,(1463,9,54)
,(1464,9,39)
,(1465,9,64)
,(1466,9,20)
,(1467,9,52)
;
INSERT INTO public.role_has_menu (id,id_role,id_menu) VALUES 
(1468,9,45)
,(1469,9,21)
,(1470,9,26)
,(1471,9,48)
,(1472,9,49)
,(1473,9,71)
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
bereputasi sekurang-kurangnya dua artikel;',4,9,2,5,1,1,4000000,6000000,2,true)
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
,(10,NULL,'Skema Penelitian Dasar Unggulan Perguruan Tinggi','PDUPT','Penelitian Dasar Unggulan Perguruan Tinggi (PDUPT) merupakan penelitian
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
mencantumkan URL artikel dimaksud; ',1,3,1,2,2,3,46000000,1500000000,1,false)
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
;

INSERT INTO public.peneliti (id_user,id_bidang_keahlian,id_organisasi,nama,nidn,beban_review_maksimal,program_studi,jenjang_pendidikan_terakhir,jabatan_akademik_fungsional,gelar_depan,gelar_belakang,tempat_lahir,tanggal_lahir,no_ktp,no_telp,no_hp,email,website_personal,alamat,last_sync_time,is_active,jenis_kelamin,is_trained,nomor_sertifikasi) VALUES 
(60,9,21,'Adita Ratnawidi','919119',10,NULL,'Sarjana','Lektor',NULL,'S. Farm','Semarang','1990-01-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(32,9,9,'Edy Susanto, SH, S.Si, M.Kes','919119',10,NULL,'Magister','Lektor',NULL,'M. Kes','Semarang','1960-01-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(1,3,1,'ars','123456',55,'Ilmu Komputer','Security','Dokter Besar','Dr.','M.Kom','bogor1','2015-09-17','1111111111111111','512345','1556161','a@g.co','https://www.google.com/','Cimohai tes',NULL,true,'P',NULL,NULL)
,(62,8,10,'Dr. Heru Subaris Kasjono. SKM. MKes','874',NULL,NULL,'Doktor','Guru Besar','Dr. ','M. Kes',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(62,15,2,'Dr. Santa Manurung, SKM, M.Kep','998',NULL,NULL,'Doktor','Lektor Kepala','Dr. ','M.Kep',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
,(39,4,7,'Dr. Iskari Ngadiarti, SKM, M.Sc','899',3,NULL,'Doktor','Lektor Kepala','Dr ','M.Sc','Jakarta','1980-10-24',NULL,'0217899065','087799099022','Iskari.jkt2@gmail.com',NULL,NULL,NULL,true,'W',NULL,NULL)
,(NULL,3,1,'Dr. drg. Jusuf Kristianto, MM, M.Kes',NULL,3,NULL,'Doktor','Guru Besar','MM','M. Kes','Jakarta','1967-02-08','6755578899999999','02177899900','087722334455','jusuf@poltekes.jakarta1.com',NULL,'Cilandak Timur',NULL,true,'P',NULL,NULL)
,(NULL,15,1,'Tarwoto, S.Kep, Ners,	 M.Kep','123',NULL,NULL,'Magister','Lektor Kepala','','M.Kep',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(NULL,4,15,'Setiawan,	SKM, M.Psi','234',NULL,NULL,'Magister','Lektor',NULL,'M.Psi',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(NULL,NULL,2,'Peneliti B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'jakarta','2019-02-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,NULL,NULL,NULL)
;
INSERT INTO public.peneliti (id_user,id_bidang_keahlian,id_organisasi,nama,nidn,beban_review_maksimal,program_studi,jenjang_pendidikan_terakhir,jabatan_akademik_fungsional,gelar_depan,gelar_belakang,tempat_lahir,tanggal_lahir,no_ktp,no_telp,no_hp,email,website_personal,alamat,last_sync_time,is_active,jenis_kelamin,is_trained,nomor_sertifikasi) VALUES 
(NULL,5,14,'Dr. Ekowati Retnaningtyas, S.Kep, M.Kes','455',NULL,NULL,'Doktor','Guru Besar','Dr. ','M. Kes',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
,(NULL,6,11,'Dr. Mahdalena, S.Pd, M.Kes','547',NULL,NULL,'Doktor','Lektor Kepala','Dr. ','M. Kes',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
,(NULL,11,12,'Dr. Rudy Hartono, SKM, M.Kes','545',NULL,NULL,'Doktor','Lektor Kepala','Dr. ','M. Kes',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'P',NULL,NULL)
,(NULL,32,13,'Nia Yuniarti Hasan, S.ST, MT','435',NULL,NULL,'Magister','Lektor',NULL,'MT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
,(NULL,35,13,'Dr. RR. Nur Fauziyah, SKM, MKM','532',NULL,NULL,'Doktor','Lektor Kepala','Dr. ','MKM',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
,(NULL,4,1,'Peneliti A',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Jkt',NULL,'155677          ','4645','08544556',NULL,NULL,NULL,NULL,true,'W',NULL,NULL)
;INSERT INTO public.penelitian (id_topik,id_skema_penelitian,id_rumpun_ilmu,id_tingkat_kesiapan_teknologi,id_tingkat_kesiapan_teknologi_target,judul,ringkasan,tahun_usulan,tahun_mulai_pelaksanaan,tahun_selesai_pelaksanaan,latar_belakang,tinjauan_pustaka,metode,daftar_pustaka,id_reviewer_skema,lokasi_penelitian,id_kelompok_tkt) VALUES 
(1,5,3,2,3,'"Manajemen Pengembangan Industri Garmen Berbasis Masyarakat untuk Mendukung Akselerasi Pembangunan Ekonomi Kabupaten Klaten"','Menjelaskan Tentang Manajemen Pengembangan Industri Garmen Berbasis Masyarakat untuk Mendukung Akselerasi Pembangunan Ekonomi, untuk contoh Kasus di Klaten','2018','2018','2019','Semakin maraknya Industri Garmen dan perkembangan Ekonomi','Buku, Jurnal, Prosiding','Kualitatif, Kuantitatif','Buku, Jurnal, Prosiding',NULL,NULL,NULL)
,(2,1,30,2,3,'"Sintesis Polimer Eugenol Tersambungsilang sebagai Antibakteri Pembersih Tangan (Hand Sanitizer)"','Ringkasan mengenai Sintesis Polimer Eugenol Tersambungsilang sebagai Antibakteri Pembersih Tangan (Hand Sanitizer)

tambahan ringkasan untuk Testing','2018','2018','2020','Hand Sanitizer yang beredar di pasaran ada beberapa yang tidak memenuhi Syarat, sehingga bisa menyebabkan iritasi kepada kulit','Kesehatan adalah keadaan seimbang yang dinamis, dipengaruhi faktor genetik, lingkungan dan pola hidup sehari-hari seperti makan, minum, seks, kerja, stirahat, hingga pengelolaan kehidupan emosional. Status kesehatan tersebut menjadi rusak bila keadaan keseimbangan terganggu, tetapi kebanyakan kerusakan pada periode-periode awal bukanlah kerusakan yang serius jika orang mau menyadarinya. (Santoso, 2012: 8)','Kuantitatif, Kualitatif, Deskriptif.....','Jurnal Nasional, Jurnal Internasional, Prosiding, Buku.....',NULL,'Poltekkes',NULL)
,(1,2,15,2,3,'Pengembangan Material Anorganik Multifungsi Hybrid Metal-based Carbon Nanoparticles untuk Remediasi Limbah Zat Warna',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,10,NULL,NULL)
,(2,6,11,2,3,'Investigasi Stabilitas Kendaraan Roda Tiga Reverse Strike dengan Penggunaan PID Controller',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,9,NULL,NULL)
,(2,1,4,2,3,'Strategi Pengembangan Kapasitas Stakeholders untuk Meningkatkan Partisipasi Aktif Forum Anak Surakarta dalam Musyawarah Perencanaan Pembangunan',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(2,6,5,2,3,'Produksi Karbon Aktif dari Ampas Mangrove Sisa Hasil Ekstraksi Pembuatan Zat Warna Alami untuk Penyerapan Gas H2S dan CO2 dalam Biogas.',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(10,2,6,2,3,'Peer Education Strategy untuk Meningkatkan Pengetahuan Remaja tentang Kesehatan Reproduksi sebagai Upaya Mengurangi Penggunaan Prostitusi',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(10,8,8,2,3,'Pengembangan Poli Asam Laktat Berberat Molekul Rendah Sebagai Matrik Pupuk Urea Dalam Bentuk "Slow Release Fertilizer"',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(10,1,9,2,3,'Pemanfaatan radiasi untuk pengembangan padi beras hitam berkualitas dan berdaya hasil tingg',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(11,2,4,2,3,'Model Pengembangan Bank Sampah Berbasis Masyarakat untuk Mendukung Pengelolaan Lingkungan Hidup dan Pembangunan Ekonomi Lokal','Menjelaskan Tentang Pengembangan Bank Sampah Berbasis Masyarakat untuk Mendukung Pengelolaan Lingkungan Hidup dan Pembangunan Ekonomi Lokal','2018','2019','2019','Semakin banyak nya sampah yang bertebaran, dan tidak ada pemisah antara samapah organik, non organik dan plastik, sehingga tidak terurainya sampah-sampah plastik karen di satukan dengan sampah organik dan non organik','Buku, Jurnal','Kualitatif, Kuantitatif','Buku, Jurnal',NULL,NULL,NULL)
;
INSERT INTO public.penelitian (id_topik,id_skema_penelitian,id_rumpun_ilmu,id_tingkat_kesiapan_teknologi,id_tingkat_kesiapan_teknologi_target,judul,ringkasan,tahun_usulan,tahun_mulai_pelaksanaan,tahun_selesai_pelaksanaan,latar_belakang,tinjauan_pustaka,metode,daftar_pustaka,id_reviewer_skema,lokasi_penelitian,id_kelompok_tkt) VALUES 
(11,5,32,2,3,'Karakterisasi sifat fisik, kimia dan biologi serat kayu aren untuk substrat hidroponik tanaman sayuran',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(11,8,35,2,3,'Situs Bersejarah Kota Surakarta Sebagai Sumber Ide Seni Kerajinan Batik Untuk Suvenir di Sentra Batik Laweyan Surakarta',NULL,'2018','2019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,7,NULL,NULL,NULL,'Penelitian Test1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,4,NULL,NULL,NULL,'Penelitian Test1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,2,NULL,NULL,NULL,'test Judul baru 20 05 2020',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,4,NULL,NULL,NULL,'Test2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,2,NULL,NULL,NULL,'Test judul Baru hari ini 20 05 2020',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,6,NULL,NULL,NULL,'gffthetasfas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,1,NULL,NULL,NULL,'asdasdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,1,NULL,NULL,NULL,'asdasdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
;
INSERT INTO public.penelitian (id_topik,id_skema_penelitian,id_rumpun_ilmu,id_tingkat_kesiapan_teknologi,id_tingkat_kesiapan_teknologi_target,judul,ringkasan,tahun_usulan,tahun_mulai_pelaksanaan,tahun_selesai_pelaksanaan,latar_belakang,tinjauan_pustaka,metode,daftar_pustaka,id_reviewer_skema,lokasi_penelitian,id_kelompok_tkt) VALUES 
(NULL,4,NULL,NULL,NULL,'test',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,4,NULL,NULL,NULL,'test aja dulu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(1,5,3,2,3,'"Manajemen Pengembangan Industri Garmen Berbasis Masyarakat untuk Mendukung Akselerasi Pembangunan Ekonomi Kabupaten Klaten"','Menjelaskan Tentang Manajemen Pengembangan Industri Garmen Berbasis Masyarakat untuk Mendukung Akselerasi Pembangunan Ekonomi, untuk contoh Kasus di Klaten','2020','2020','2023','Semakin maraknya Industri Garmen dan perkembangan Ekonomi','Buku, Jurnal, Prosiding','Kualitatif, Kuantitatif','Buku, Jurnal, Prosiding',18,NULL,NULL)
,(1,4,3,NULL,NULL,'Penelitian pada Tanggal 22 Mei 2020',NULL,NULL,'NaN ','NaN ',NULL,NULL,NULL,NULL,NULL,'Bogor',NULL)
,(NULL,4,NULL,NULL,NULL,'Test 1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(NULL,2,NULL,NULL,NULL,'Tes untuk workflow',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(3,4,6,1,2,'test32',NULL,NULL,'2030','2031',NULL,NULL,NULL,NULL,NULL,NULL,1)
,(NULL,8,NULL,NULL,NULL,'Test Validasi Sukses',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(1,4,6,1,2,'test',NULL,NULL,'2021','2022',NULL,NULL,NULL,NULL,NULL,NULL,1)
,(1,4,6,1,2,'Test76',NULL,NULL,'2030','2031',NULL,NULL,NULL,NULL,NULL,NULL,1)
;
INSERT INTO public.penelitian (id_topik,id_skema_penelitian,id_rumpun_ilmu,id_tingkat_kesiapan_teknologi,id_tingkat_kesiapan_teknologi_target,judul,ringkasan,tahun_usulan,tahun_mulai_pelaksanaan,tahun_selesai_pelaksanaan,latar_belakang,tinjauan_pustaka,metode,daftar_pustaka,id_reviewer_skema,lokasi_penelitian,id_kelompok_tkt) VALUES 
(1,4,3,1,2,'test77',NULL,NULL,'2020','2021',NULL,NULL,NULL,NULL,NULL,NULL,1)
,(3,2,3,10,11,'Test Judul Baru 20 05 2020',NULL,NULL,'2021','2023',NULL,NULL,NULL,NULL,NULL,NULL,2)
,(11,2,4,2,3,'Model Pengembangan Bank Sampah Berbasis Masyarakat untuk Mendukung Pengelolaan Lingkungan Hidup dan Pembangunan Ekonomi Lokal','Menjelaskan Tentang Pengembangan Bank Sampah Berbasis Masyarakat untuk Mendukung Pengelolaan Lingkungan Hidup dan Pembangunan Ekonomi Lokal','2020','2020','2022','Semakin banyak nya sampah yang bertebaran, dan tidak ada pemisah antara samapah organik, non organik dan plastik, sehingga tidak terurainya sampah-sampah plastik karen di satukan dengan sampah organik dan non organik','Buku, Jurnal','Kualitatif, Kuantitatif','Buku, Jurnal',NULL,NULL,NULL)
,(6,4,3,NULL,NULL,'test','1"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."','2019','2019','2020','2"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."','3"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."','4"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."','5"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."',NULL,'bandung',NULL)
,(6,9,19,20,21,'Penelitian Dasar Unggulan PT Desentralisasi 09 Juni 2020','Efektifitas Kelas Pijat Bayi terhadap Kemampuan Orang Tua Dalam Praktik Pijat Bayi',NULL,'2020','2022','Bayi yang baru dilahirkan kadang selalu di timang untuk di tenangkan, atau banyak keluarga atau saudara yang ikut menimang bayi mungil tersebut, sehingga membuat Bayi sering menangis karena kemungkinan Bayi tersebut tidak enak Badannya','Buku
Makalah Ilmiah
Jurnal Ilmiah
Seminar Nasional & Internasional','Kuantitatif
Kualitatif
Survey','Buku
Jurnal
Seminar Nasional
Seminar Internasional',NULL,NULL,3)
,(1,4,3,5,6,'Penelitian pada Tanggal 08 Juni 2020','Penelitian yang dilakukan pada Tanggal 8 Juni Tahun 2020',NULL,'2020','2021','Latar Belakang Penelitian berdasarkan Survey serta kejadian yang sekarang sedang Terjadi','1. Esiklopedi
2. Buku
3. Jurnal Ilmiah
4. Karya Ilmiah
5. Makalah Seminar Nasional
6. Makalah Seminar Internasional
','1. Metode penelitian kualitatif
2. Metode penelitian kuantitatif
3. Metode penelitian survei
4. Metode penelitian deskriptif','Bryan, M. 2015. Up & Running with PageMaker 4 (Bekerja dengan PageMaker 4). Jakarta: PT Elex Media Komputindo.
Bennet, D.C. 2017. English Preposition: a Stratificational Approach. Journal of Linguistics. 4(1): 153-172.
Jusmadi. 2018. Perkembangan Beberapa Vaksin untuk Menangani Penyakit yang belum ada Obatnya, (Online), Vol. 5, No. 4, (http://www.jusmadi-bakat-minat.blogspot.com, diakses 17 Februari 2018).',NULL,NULL,1)
,(6,4,10,14,15,'Penelitian Pemula Desentralisasi 08 Juni 2020','Ringkasan Penelitian',NULL,'2020','2021','Penelitian ini dilakukan dikarenakan Vaksin Covid-19 belum ada','Jurnal Ilmiah, Makalah Seminar, Buku, Karya Ilmiah','Kualitatif, Kuantitatif, Survey','Buku, Jurnal, Makalah',NULL,NULL,2)
,(1,4,4,1,2,'test123',NULL,NULL,'2020','2020',NULL,NULL,NULL,NULL,NULL,NULL,1)
,(6,2,22,14,15,'Judul Penelitian Desentralisasi Terapan Unggulan PT 08 Juni 2020',NULL,NULL,'2021','2023',NULL,NULL,NULL,NULL,NULL,NULL,2)
,(2,2,3,1,2,'test test',NULL,NULL,'2020','2022',NULL,NULL,NULL,NULL,NULL,NULL,1)
;


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