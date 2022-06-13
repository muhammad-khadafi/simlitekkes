# SIMLITEKKES Backend _(simlitekkes-backend)_

Layanan _backend_ SIMLITEKKES.

## Pipeline Status

- `master`
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/master/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/master)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/master/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/master)
- `testing`
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/testing/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/testing)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/testing/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/testing)
- `develop`
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/develop/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/develop)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/badges/develop/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend/commits/develop)

## Setup

Untuk mulai berkontribusi pada proyek ini, pastikan _tools_ berikut telah
terpasang di mesin pengembangan Anda:

- [AdoptOpenJDK 11](https://adoptopenjdk.net/)
  > Disarankan untuk menggunakan AdoptOpenJDK 11 karena pihak Oracle akan
  > berhenti memberikan dukungan secara gratis terhadap Java 8 pada akhir
  > tahun 2020. Silakan baca lebih lanjut di tautan berikut:
  > [Oracle Java SE Support Roadmap](https://www.oracle.com/java/technologies/java-se-support-roadmap.html)
- [Maven 3.6](https://maven.apache.org/download.cgi)
- Node.js LTS 10 dan [Postman](https://www.postman.com)
  > Node.js dan Postman digunakan untuk membuat dan menjalankan
  > _functional test_. Panduan pemasangan Node.js dapat dilihat di
  > [README `frontend`](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/blob/develop/README.md) dan panduan
  > "Persiapan Mesin Pengembangan" di [Wiki Project Management](https://gitlab.pusilkom.com/ppsdm/project-management/wikis/home).
- _Database_ [PostgreSQL 12](https://www.postgresql.org/download/)
- _Text editor_ atau IDE favorit Anda.

Setelah pemasangan _tools_, pastikan juga beberapa _environment variable_
berikut juga telah diatur sesuai dengan sistem operasi yang digunakan:

- `JAVA_HOME`: Lokasi instalasi OpenJDK. Misal: `/usr/local/java`,
  `C:\Program Files\AdoptOpenJDK\jdk-11.0.4+11`.

Ada skema dan data awal _database_ yang bisa ditemukan pada berkas [`initial_script.sql`](initial_script.sql).
Silakan gunakan berkas SQL tersebut untuk menyiapkan database dan
mengisinya dengan data awal.

> Tips: Jika mampu menjalankan _container_ (misal: menggunakan Docker),
> di mesin pengembangan lokal, disarankan untuk memiliki sebuah _container_
> yang menjalankan _database_ selama proses pengembangan berlangsung supaya
> tidak mengotori mesin pengembangan lebih lanjut (karena harus memasang lebih
> dari satu _database_).

Apabila _tools_ dan _database_ telah siap, silakan _clone_ repositori kode ke
mesin lokal dan jalankan _test suite_ untuk memancing Maven agar mengunduh
seluruh _dependency_ yang dibutuhkan:

```bash
# Pilih salah satu: clone repositori melalui protokol SSH
git clone git@gitlab.pusilkom.com:ppsdm/simlitekkes-backend.git
# Atau, clone repositori melalui protokol HTTPS
git clone https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend.git
cd simlitekkes-backend
mvn clean verify
```

Untuk menjalankan _backend_ secara lokal di _port_ 8001:

```bash
mvn clean spring-boot:run
```

> Catatan: Nomor _port_ bisa diatur pada berkas
> [`application.properties`](src/main/resources/application.properties).

Apabila ingin membuat berkas .JAR untuk keperluan _deploy_:

```bash
mvn package -DskipTests
```

Berkas JAR yang dihasilkan dapat ditemukan di folder [`target`](target)
dan bisa dijalankan dengan perintah:

```bash
java -jar ./target/[NAMA_BERKAS_JAR].jar
```

Apabila ingin menimpa (_override_) konfigurasi _backend_ yang telah
di-_build_ menjadi berkas JAR, maka buatlah berkas `application.properties`
di dalam folder yang sama dengan berkas JAR lalu tuliskan _property_
serta nilainya yang ingin berbeda. Alternatif lain adalah menggunakan
_environment variable_ di sistem operasi yang digunakan.

> Catatan: Silakan baca lebih lanjut mengenai mekanisme Spring Boot dalam
> menangani konfigurasi di [dokumentasi Spring](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files).
> Sebagai gambaran, _environment_ `develop` dan `testing` menggunakan
> _environment variable_ untuk menimpa konfigurasi supaya dapat operasional
> dan memenuhi kebutuhan keamanan di sisi server.

## Functional Testing

_Functional test_ digunakan untuk menguji coba `backend` ketika sudah berada
di _environment_ tujuan. Uji coba dilakukan dengan pemanggilan kumpulan
_request_ ke `backend` dan membandingkan apakah _response_ yang dikembalikan
sesuai dengan ekspektasi.

Pada proyek ini, _functional test_ dikembangkan sebagai Postman Collection
dan dieksekusi menggunakan [`newman`](https://github.com/postmanlabs/newman).
Pasang `newman` terlebih dahulu menggunakan Node.js:

```bash
# Jika menggunakan `yarn`
yarn global add newman
# Jika menggunakan `npm`
npm install --global newman
```

Untuk menjalankan _functional test_ terhadap `backend` yang sudah _deploy_ di
{`localhost`,`develop`,`testing`}:

```bash
newman run functional_test/postman_collection.json \
       --environment functional_test/{localhost,develop,testing}.postman_environment.json
```

Konfigurasi koneksi ke masing-masing _environment_ dapat diubah di
masing-masing berkas JSON `postman_environment`. Berkas konfigurasi dan
_test cases_ berbentuk Postman Collection bisa juga diubah menggunakan
Postman. Harap merujuk ke panduan penggunaan Postman dan contoh yang telah
diberikan dalam proyek ini untuk memahami cara pembuatan _functional test_.

## Building & Running Container Image

Apabila ingin membuat _container image_ untuk keperluan uji coba di mesin
lokal:

```bash
docker build -t simlitekkes-backend:localhost .
```

Untuk menjalankannya (dan menghapus _container_ yang sudah pernah berjalan):

```bash
docker rm -f simlitekkes-backend
docker run -d -p 8001:8001 \
  --name simlitekkes-backend \
  -e SPRING_DATASOURCE_URL=[JDBC_URL_DATABASE] \
  -e SPRING_DATASOURCE_USERNAME=[USERNAME_DATABASE] \
  -e SPRING_DATASOURCE_PASSWORD=[PASSWORD_DATABASE] \
  registry.pusilkom.com/ppsdm/simlitekkes-backend:localhost
```

Apabila berhasil, _backend_ dapat diakses secara lokal di [`localhost` port 8001](http://localhost:8001).

> Catatan: [Berkas `.gitlab-ci.yml`](.gitlab-ci.yml) mengandung
> instruksi lebih lengkap mengenai proses pembuatan _container image_
> untuk keperluan _deployment_ di _environment_ `develop` dan `testing`.

_Backend_ serta komponen-komponen penyertanya juga dapat disiapkan dan
dijalankan di lokal dengan praktis menggunakan Docker Compose. Pertama, buat
berkas `.env-local` di folder [`deploy/docker`](deploy/docker). Isi berkas
tersebut dengan nilai-nilai konfigurasi yang dibutuhkan. Contoh berkas `.env`
dapat dilihat di [`deploy/docker/.env-example`](deploy/docker/.env-example).

Apabila sudah menyiapkan `.env-local` dan dengan asumsi direktori aktif _shell_
saat ini masih di `deploy/docker`, maka jalankan perintah:

```bash
docker-compose \
  -f ./docker-compose.yml -f ./docker-compose.localhost.yml \
  -p simlitekkes-backend-localhost
  up --build --detach
```

> Catatan: Opsi `--build` hanya dibutuhkan apabila ingin _build_ ulang
> _backend_. Biasanya ini dilakukan apabila ada perubahan pada _source code_.
> Jika tidak ada perubahan pada _source code_, maka opsi `--build` tidak perlu
> diikutsertakan.

Apabila berhasil, keseluruhan komponen aplikasi (termasuk database, _backend_,
dan Adminer) dapat diakses melalui port yang dialokasikan secara otomatis.
Silakan cek port ke masing-masing komponen aplikasi dengan perintah:

```bash
docker-compose \
  -f ./docker-compose.yml -f ./docker-compose.localhost.yml \
  -p simlitekkes-backend-localhost \
  ps
```

Jika ingin mematikan dan menghapus _backend_, maka jalankan perintah berikut:

```bash
docker-compose  \
  -f ./docker-compose.yml -f ./docker-compose.localhost.yml \
  -p simlitekkes-backend-localhost \
  down --volume
```

## Contributing

Sebelum memulai pekerjaan, pahami terlebih dahulu konvensi penamaan _branch_
di proyek ini. Pada dasarnya, akan ada 3 _branch_ utama dan beberapa _branch_
fitur/_bugfix_. Tiga _branch_ utama yang dimaksud adalah:

- `master`: _Branch_ yang mengandung kode sumber yang di-_build_ dan _deploy_
  untuk _environment_ `production`.
- `develop`: _Branch_ yang mengandung kode sumber hasil integrasi
  _branch-branch_ fitur dari tim pengembang dan baru diuji secara lokal.
  _Commit_ terakhir di _branch_ ini akan di-_build_ dan _deploy_ ke
  _environment_ `develop`. **Harap tim pengembang menguji coba fungsionalitas
  _backend_ di _environment_ `develop` sebelum memasukkan kode sumber ke
  _branch_ `testing` dan diuji oleh QA**.
- `testing`: _Branch_ yang mengandung kode sumber untuk diuji oleh tim QA.
  _Commit_ terakhir di _branch_ ini akan di-_build_ dan _deploy_ ke
  _environment_ `testing`.

Untuk pengembangan fitur dan _bugfix_, ketentuan penamaan _branch_-nya adalah
sebagai berikut:

- Fitur baru: `f-[ID]`
- _Bugfix_: `b-[ID]`

> Catatan: `[ID]` adalah nomor ID _task_ atau _issue_ terkait di sistem
> manajemen proyek yang digunakan (misal: GitLab _issue tracker_)

## Contributors

Daftar kontributor dapat dilihat dengan kombinasi perintah Git pada _shell_
berikut:

```bash
git log --format=%an | sort | uniq
```

## License

Copyright (c) 2020 Pusat Ilmu Komputer Universitas Indonesia. All rights
reserved.
