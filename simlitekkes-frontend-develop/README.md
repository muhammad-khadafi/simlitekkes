# SIMLITEKKES Frontend _(simlitekkes-frontend)_

Layanan _frontend_ SIMLITEKKES.

## Pipeline Status

- `master`
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/master/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/master)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/master/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/master)
- [`testing`](https://testing-simlitekkes.pusilkom.ui.ac.id)
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/testing/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/testing)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/testing/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/testing)
- [`develop` (via VPN Pusilkom)](http://10.119.234.223:10000)
  > [![pipeline status](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/develop/pipeline.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/develop)
  > [![coverage report](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/badges/develop/coverage.svg)](https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend/commits/develop)

## Setup

Untuk mulai berkontribusi pada proyek ini, pastikan _tools_ berikut telah
terpasang dan dapat dipanggil dari _shell_ favorit Anda:

- [Node.js versi 10.19.0](https://nodejs.org/dist/latest-v10.x/)

  > Catatan: Gunakan `nvm` (Node Version Manager) untuk manajemen instalasi
  > Node.js di komputer. Unduh: [`nvm` (Windows)](https://github.com/coreybutler/nvm-windows),
  > [`nvm` (Non-Windows)](https://github.com/nvm-sh/nvm)
- [_Package manager_ `yarn` v1.x (_classic_)](https://yarnpkg.com/lang/en/)

Silakan ikuti _shell script_ berikut untuk memastikan `node` dan `yarn` telah
terpasang dan dapat dipanggil:

```bash
$ node --version
v10.19.0
$ yarn --version
1.22.4
```

Silakan _clone_ repositori kode ke mesin lokal menggunakan Git dan masuk ke
dalam direktori kode sumber:

```bash
# Pilih salah satu: clone repositori melalui protokol SSH (disarankan jika familiar)
git clone git@gitlab.pusilkom.com:ppsdm/simlitekkes-frontend.git
# Atau, clone repositori melalui protokol HTTPS
git clone https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend.git
cd simlitekkes-frontend
```

Selanjutnya, unduh _package_-_package_ yang dibutuhkan dengan memanggil:

```bash
# Jika menggunakan `yarn`
yarn install
# Jika menggunakan `npm`
npm install
```

Agar dapat menyambungkan _frontend_ dengan _backend_ yang berada di mesin
lokal ataupun di _environment_ `develop`/`testing`, buatlah berkas `.env`
terlebih dahulu dan isi berkas tersebut dengan satu variabel bernama
`VUE_APP_BACKEND_URL`. Isi variabel tersebut dengan alamat _backend_, misal:
`http://localhost:8001`.

Contoh cara pembuatan berkas `.env` secara singkat:

```bash
echo "VUE_APP_BACKEND_URL=http://localhost:8001" > .env
```

> Catatan: Silakan lihat contoh berkas `.env-example` sebagai contoh
> penulisan berkas `.env`.

Setiap kali melakukan perubahan pada kode, jangan lupa untuk memanggil
_linter_ yang akan memeriksa penerapan _style_ penulisan kode:

```bash
# Jika menggunakan `yarn`
yarn lint
# Jika menggunakan `npm`
npm run lint
```

Untuk menjalankan _frontend_ dan melihat hasilnya secara lokal di
[`localhost` _port_ 8080](http://localhost:8080):

```bash
# Jika menggunakan `yarn`
yarn dev
# Jika menggunakan `npm`
npm run dev
```

Untuk membuat _build_ yang siap dijalankan di `production`:

```bash
# Jika menggunakan `yarn`
yarn build --mode production
# Jika menggunakan `npm`
npm run build --mode production
```

## Building & Running Container Image

Pastikan terlebih dahulu mesin lokal telah terpasang _engine_ Docker, _client_
Docker CLI yang tersambung ke _engine_ Docker tujuan (biasanya lokal), dan
program [`docker-compose`](https://docs.docker.com/compose/install/):

```bash
$ docker version
Client: Docker Engine - Community
 Version:           19.03.8
 API version:       1.40
...
Server: Docker Engine - Community
 Engine:
  Version:          19.03.8
  API version:      1.40 (minimum version 1.12)
...
$ docker --version
Docker version 19.03.8, build afacb8b
$ docker-compose --version
docker-compose version 1.25.4, build 8d51620a
```

Kemudian atur nilai _environment variable_ `VUE_APP_BACKEND_URL` di dalam
berkas `.env`:

```bash
echo "VUE_APP_BACKEND_URL=[ALAMAT_BACKEND]" > .env
```

Jalankan perintah `docker-compose` berikut untuk memulai proses _build_
hingga _deploy container_ di mesin lokal:

```bash
docker-compose up --build --detach
```

Agar dapat mengakses _frontend_ yang telah berjalan sebagai _container_ dari
mesin lokal (_host_), cek terlebih dahulu _port_ yang dialokasikan oleh _host_
menuju _container_:

```bash
$ docker-compose ps
       Name                 Command          State           Ports
--------------------------------------------------------------------------
frontend_frontend_1   nginx -g daemon off;   Up      0.0.0.0:32769->80/tcp
```

Pada contoh di atas, _port_ `32769` adalah _port_ yang dapat digunakan oleh
_client_ di mesin lokal agar dapat mengakses _frontend_ di dalam _container_.
Silakan buka `localhost` _port_ `32769` di _browser_ Web.

Jika ingin menghentikan _container_:

```bash
docker-compose down
docker-compose rm --stop
```

Apabila ingin membangun _image_ dan menjalankannya sebagai _container_
secara manual tanpa bantuan `docker-compose`, maka bisa ikuti langkah-langkah
berikut. Pertama, bangun _image_ menggunakan `docker build`:

```bash
docker build \
  --build-arg BUILD_MODE=development \
  --build-arg VUE_APP_BACKEND_URL=[ALAMAT_BACKEND] \
  --tag simlitekkes-frontend:localhost .
```

Lalu hapus _container_ yang sedang menjalankan _frontend_ versi sebelumnya:

```bash
docker container rm -f simlitekkes-frontend
```

Jalankan _container_ menggunakan _image_ yang baru saja dibuat:

```bash
docker run --detach --name simlitekkes-frontend -P simlitekkes-frontend:localhost
```

Kemudian cek pemetaan _port_ dari _container_ ke _host_:

```bash
$ docker ps
CONTAINER ID        IMAGE                            COMMAND                  CREATED             STATUS              PORTS                   NAMES
41ea96126371        simlitekkes-frontend:localhost   "nginx -g 'daemon of…"   6 seconds ago       Up 6 seconds        0.0.0.0:32768->80/tcp   simlitekkes-frontend
```

Pada contoh di atas, _port_ `32768` adalah _port_ yang dipetakan oleh _host_
ke _port_ `80` di dalam _container_. Silakan kunjungi `localhost:32768`
melalui _browser_ Web.

> Catatan: Berkas [`.gitlab-ci.yml`](.gitlab-ci.yml) mengandung instruksi lebih
> lengkap mengenai proses pembuatan _container image_ untuk keperluan _deployment_
> di _environment_ `develop` dan `testing`.

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
  _frontend_ di _environment_ `develop` sebelum memasukkan kode sumber ke
  _branch_ `testing` dan diuji oleh QA**.
- `testing`: _Branch_ yang mengandung kode sumber untuk diuji oleh tim QA.
  _Commit_ terakhir di _branch_ ini akan di-_build_ dan _deploy_ ke
  _environment_ `testing`.

Untuk pengembangan fitur dan _bugfix_, ketentuan penamaan _branch_-nya adalah
sebagai berikut:

- Fitur baru: `f-[ID]`
- _Bugfix_: `b-[ID]`

> `[ID]` adalah nomor ID _task_ atau _issue_ terkait fitur/bug di sistem manajemen
> proyek yang digunakan (misal: GitLab _issue tracker_).

## Contributors

Daftar kontributor dapat dilihat dengan kombinasi perintah berikut:

```bash
git log --format=%an | sort | uniq
```

## License

Copyright (c) 2020 Pusat Ilmu Komputer Universitas Indonesia. All rights
reserved.

_Template_ menggunakan [Vuetify Material Dashboard versi 2.0.0](https://github.com/creativetimofficial/vuetify-material-dashboard/tree/v2.0.0)
dengan lisensi [MIT](https://github.com/creativetimofficial/vuetify-material-dashboard/blob/v2.0.0/LICENSE.md).
