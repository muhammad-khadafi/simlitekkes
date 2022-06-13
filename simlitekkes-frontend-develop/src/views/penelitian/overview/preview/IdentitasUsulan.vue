<template>
  <v-container>
    <v-row>
      <v-col
        cols="12"
        md="12"
      >
        <material-card>
          <v-row>
            <v-col
              cols="6"
              md="6"
            >
              <v-simple-table>
                <tbody class="body-1 text--primary">
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Judul
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.judul }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Nama TKT
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.namaTkt }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    TKT Awal
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.levelSaatIni }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    TKT Target
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.levelTarget }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Kategori
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.namaKategori }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Rumpun Ilmu
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.rumpunIlmu }}
                  </td>
                </tr>
                </tbody>
              </v-simple-table>
            </v-col>
            <v-col
              cols="6"
              md="6"
            >
              <v-simple-table>
                <tbody class="body-1 text--primary">
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Tahun Mulai
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.tahunMulaiPelaksanaan }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Tahun Selesai
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.tahunSelesaiPelaksanaan }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Skema
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.skema }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Tema
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.tema }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Topik
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.topik }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Topik Unggulan
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ peneliti.topikUnggulan }}
                  </td>
                </tr>
                <tr>
                  <td
                    style="width: 25%"
                  >
                    Lokasi Penelitian
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ usulanDetail.lokasiPenelitian === null ? '-' : usulanDetail.lokasiPenelitian }}
                  </td>
                </tr>
                </tbody>
              </v-simple-table>
            </v-col>
          </v-row>
        </material-card>
      </v-col>
      <v-col
        cols="6"
        md="6"
      >
        <material-card
          color="blue"
          title="Identitas Pengusul - Ketua"
        >
          <v-simple-table>
            <template v-slot:default>
              <tbody class="body-1 text--primary">
                <tr>
                  <td
                    style="width: 15%"
                  >
                    Nama
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ peneliti.gelarDepan }} {{ peneliti.nama }} {{ peneliti.gelarBelakang }}
                  </td>
                </tr>
                <tr>
                  <td>
                    NIDN
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.nidn }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Institusi
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.namaInstitusi }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Program Studi
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.programStudi }}
                  </td>
                </tr>
                <tr>
                  <td>
                    ID Sinta
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    -
                  </td>
                </tr>
                <tr>
                  <td>
                    Jenjang
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.jenjangPendidikanTerakhir }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Email
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.email }}
                  </td>
                </tr>
                <tr>
                  <td>Rekam Jejak</td>
                  <td>:</td>
                  <td>
                    <ol
                      type="1"
                    >
                      <li
                        v-for="icon in rekamJejak"
                        :key="icon.id"
                        style="margin-top: 5px"
                      >
                        {{ icon.nama + " (" + icon.jumlahOutput + ")" }}
                      </li>
                    </ol>
                  </td>
                </tr>
                <tr>
                  <td colspan="3">
                    <v-card class="mt-6">
                      <v-tabs
                        v-model="tab"
                        background-color="transparent"
                        color="green"
                      >
                        <v-tab :key="'/penelitian'">
                          Penelitian
                        </v-tab>
                        <v-tab :key="'/jurnal'">
                          Jurnal
                        </v-tab>
                        <v-tab :key="'/hki'">
                          HKI
                        </v-tab>
                        <v-tab :key="'/prosiding'">
                          Prosiding
                        </v-tab>
                        <v-tab :key="'/buku'">
                          Buku
                        </v-tab>
                      </v-tabs>

                      <v-tabs-items v-model="tab">
                        <v-tab-item :key="'/penelitian'">
                          <v-data-table
                            :headers="headersPenelitian"
                            :items="listPenelitian"
                            sort-by="judul"
                            class="elevation-1"
                            :loading="isLoading"
                            loading-text="Loading... Please wait"
                            :search="searchPenelitian"
                          >
                            <template v-slot:top>
                              <v-toolbar>
                                <v-toolbar-title>Daftar Penelitian</v-toolbar-title>
                              </v-toolbar>
                              <v-divider />
                              <v-toolbar
                                flat
                                class="mt-1"
                              >
                                <v-text-field
                                  v-model="searchPenelitian"
                                  append-icon="mdi-magnify"
                                  label="Cari"
                                  single-line
                                  hide-details
                                  class="search"
                                />
                                <div class="flex-grow-1" />
                              </v-toolbar>
                            </template>
                            <template v-slot:item.berkas="{ item }">
                              <v-icon
                                large
                                @click="downloadItem(item)"
                                :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
                              >
                                mdi-file-pdf
                              </v-icon>
                            </template>
                            <template v-slot:no-data />
                          </v-data-table>
                        </v-tab-item>
                        <v-tab-item :key="'/jurnal'">
                          <v-data-table
                            :headers="headers"
                            :items="listJurnal"
                            sort-by="judul"
                            class="elevation-1"
                            :loading="isLoading"
                            loading-text="Loading... Please wait"
                            :search="searchJurnal"
                          >
                            <template v-slot:top>
                              <v-toolbar>
                                <v-toolbar-title>Daftar Jurnal</v-toolbar-title>
                              </v-toolbar>
                              <v-divider />
                              <v-toolbar
                                flat
                                class="mt-1"
                              >
                                <v-text-field
                                  v-model="searchJurnal"
                                  append-icon="mdi-magnify"
                                  label="Cari"
                                  single-line
                                  hide-details
                                  class="search"
                                />
                                <div class="flex-grow-1" />
                              </v-toolbar>
                            </template>
                            <template v-slot:item.berkas="{ item }">
                              <v-icon
                                large
                                @click="downloadItem(item)"
                                :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
                              >
                                mdi-file-pdf
                              </v-icon>
                            </template>
                            <template v-slot:no-data />
                          </v-data-table>
                        </v-tab-item>
                        <v-tab-item :key="'/hki'">
                          <v-data-table
                            :headers="headers"
                            :items="listHki"
                            sort-by="judul"
                            class="elevation-1"
                            :loading="isLoading"
                            loading-text="Loading... Please wait"
                            :search="searchHki"
                          >
                            <template v-slot:top>
                              <v-toolbar>
                                <v-toolbar-title>Daftar HKI</v-toolbar-title>
                              </v-toolbar>
                              <v-divider />
                              <v-toolbar
                                flat
                                class="mt-1"
                              >
                                <v-text-field
                                  v-model="searchHki"
                                  append-icon="mdi-magnify"
                                  label="Cari"
                                  single-line
                                  hide-details
                                  class="search"
                                />
                                <div class="flex-grow-1" />
                              </v-toolbar>
                            </template>
                            <template v-slot:item.berkas="{ item }">
                              <v-icon
                                large
                                @click="downloadItem(item)"
                                :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
                              >
                                mdi-file-pdf
                              </v-icon>
                            </template>
                            <template v-slot:no-data />
                          </v-data-table>
                        </v-tab-item>
                        <v-tab-item :key="'/prosiding'">
                          <v-data-table
                            :headers="headers"
                            :items="listProsiding"
                            sort-by="judul"
                            class="elevation-1"
                            :loading="isLoading"
                            loading-text="Loading... Please wait"
                            :search="searchProsiding"
                          >
                            <template v-slot:top>
                              <v-toolbar>
                                <v-toolbar-title>Daftar Prosiding</v-toolbar-title>
                              </v-toolbar>
                              <v-divider />
                              <v-toolbar
                                flat
                                class="mt-1"
                              >
                                <v-text-field
                                  v-model="searchProsiding"
                                  append-icon="mdi-magnify"
                                  label="Cari"
                                  single-line
                                  hide-details
                                  class="search"
                                />
                                <div class="flex-grow-1" />
                              </v-toolbar>
                            </template>
                            <template v-slot:item.berkas="{ item }">
                              <v-icon
                                large
                                @click="downloadItem(item)"
                                :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
                              >
                                mdi-file-pdf
                              </v-icon>
                            </template>
                            <template v-slot:no-data />
                          </v-data-table>
                        </v-tab-item>
                        <v-tab-item :key="'/buku'">
                          <v-data-table
                            :headers="headers"
                            :items="listBuku"
                            sort-by="judul"
                            class="elevation-1"
                            :loading="isLoading"
                            loading-text="Loading... Please wait"
                            :search="searchBuku"
                          >
                            <template v-slot:top>
                              <v-toolbar>
                                <v-toolbar-title>Daftar Buku</v-toolbar-title>
                              </v-toolbar>
                              <v-divider />
                              <v-toolbar
                                flat
                                class="mt-1"
                              >
                                <v-text-field
                                  v-model="searchBuku"
                                  append-icon="mdi-magnify"
                                  label="Cari"
                                  single-line
                                  hide-details
                                  class="search"
                                />
                                <div class="flex-grow-1" />
                              </v-toolbar>
                            </template>
                            <template v-slot:item.berkas="{ item }">
                              <v-icon
                                large
                                @click="downloadItem(item)"
                                :disabled="item.idPenelitian != null && item.idPeranPeneliti != 1"
                              >
                                mdi-file-pdf
                              </v-icon>
                            </template>
                            <template v-slot:no-data />
                          </v-data-table>
                        </v-tab-item>
                      </v-tabs-items>
                    </v-card>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </material-card>
      </v-col>
      <!--<v-col
        cols="6"
        md="6"
        v-if="isReviewer"
      >
        <material-card
          color="blue"
          title="Identitas Pengusul - Ketua"
        >
          <v-simple-table>
            <template v-slot:default>
              <tbody class="body-1 text&#45;&#45;primary">
              <tr>
                <td>Rekam Jejak</td>
                <td>:</td>
                <td>
                  <ol
                    type="1"
                  >
                    <li
                      v-for="icon in rekamJejak"
                      :key="icon.id"
                      style="margin-top: 5px"
                    >
                      {{ icon.nama + " (" + icon.jumlahOutput + ")" }}
                    </li>
                  </ol>
                </td>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
        </material-card>
      </v-col>-->
      <v-col
        cols="6"
        md="6"
      >
        <material-card
          color="blue"
          title="Identitas Pengusul - Anggota"
        >
          <v-data-table
            :headers="headersP"
            :items="timPenelitiP"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="searchP"
          >
            <template v-slot:top>
              <v-toolbar>
                <v-toolbar-title>Daftar Peneliti Poltekkes</v-toolbar-title>
              </v-toolbar>
              <v-divider />
              <v-toolbar
                flat
                class="mt-1"
              >
                <v-text-field
                  v-model="searchP"
                  append-icon="mdi-magnify"
                  label="Search"
                  single-line
                  hide-details
                  class="search"
                />
              </v-toolbar>
            </template>
            <template v-slot:item.detailNama="{ item }">
              {{item.gelarDepan}} {{item.nama}} {{item.gelarBelakang}}
            </template>
          </v-data-table>
          <v-data-table
            :headers="headersNP"
            :items="timPenelitiNP"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="searchNP"
          >
            <template v-slot:top>
              <v-toolbar>
                <v-toolbar-title>Daftar Peneliti Non Poltekkes</v-toolbar-title>
              </v-toolbar>
              <v-divider />
              <v-toolbar
                flat
                class="mt-1"
              >
                <v-text-field
                  v-model="searchNP"
                  append-icon="mdi-magnify"
                  label="Search"
                  single-line
                  hide-details
                  class="search"
                />
              </v-toolbar>
            </template>
            <template v-slot:item.detailNama="{ item }">
              {{item.gelarDepan}} {{item.nama}} {{item.gelarBelakang}}
            </template>
          </v-data-table>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import {apiServer, fileUrl} from '../../../../setup-axios'
  import MaterialCard from '../../../../components/material/Card'
  import axios from 'axios'

  export default {
    components: { MaterialCard },
    data: () => ({
      searchP: '',
      searchNP: '',
      isLoading: true,
      usulanDetail: {},
      pengusulan: {
        idKategori: null,
        idSkemaPenelitian: null,
        klasifikasi: null,
        judul: '',
        username: null,
      },
      timPenelitiNP: [],
      timPenelitiP: [],
      peneliti: {},
      rekamJejak: [],
      isReviewer: null,
      listPenelitian: [],
      listJurnal: [],
      listProsiding: [],
      listHki: [],
      listBuku: [],
      tab: null,
      headersP: [
        {
          text: 'Nama',
          align: 'center',
          value: 'detailNama',
        },
        {
          text: 'Organisasi',
          align: 'center',
          value: 'namaInstitusi',
        },
        {
          text: 'Tugas Penelitian',
          align: 'center',
          value: 'tugasPenelitian',
        },
      ],
      headersNP: [
        {
          text: 'Nama',
          align: 'center',
          value: 'detailNama',
        },
        {
          text: 'Organisasi',
          align: 'center',
          value: 'namaInstitusi',
        },
        {
          text: 'Tugas Penelitian',
          align: 'center',
          value: 'tugasPenelitian',
        },
      ],
      headers: [
        { text: 'Berkas', value: 'berkas', align: 'center', sortable: false },
        {
          text: 'Judul',
          align: 'left',
          value: 'judul',
        },
        {
          text: 'Jenis Publikasi',
          align: 'left',
          value: 'namaJenisOutput',
        },
        {
          text: 'URL',
          align: 'left',
          value: 'urlOutput',
        },
      ],
      headersPenelitian: [
        { text: 'Judul', value: 'judul' },
        { text: 'Tahun Pelaksanaan', value: 'tahunPelaksanaan' },
        { text: 'Peran', value: 'namaPeran' },
      ],
      editedIndex: -1,
      searchJurnal: '',
      searchHki: '',
      searchProsiding: '',
      searchBuku: '',
      searchPenelitian: '',
    }),

    created () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.$store.state.user.role.roleCode === 'REVIEWER') this.isReviewer = true
        this.isLoading = true
        apiServer
          .get('/timPeneliti/getDataKetua/' + this.$route.params.id)
          .then(response => {
            var dataKetua = response.data
            axios.all([
              apiServer.get('/jenis-output/info-jumlah/' + dataKetua.idUser),
              apiServer.get('/timPeneliti/getDataAnggotaNP/' + this.$route.params.id),
              apiServer.get('/timPeneliti/getDataAnggota/' + this.$route.params.id),
              apiServer.get('/peneliti/' + dataKetua.idPeneliti),
              apiServer.get('/identitas-usulan/preview/' + this.$route.params.id),
              apiServer.get('/jurnal/' + dataKetua.idUser),
              apiServer.get('/hki/' + dataKetua.idUser),
              apiServer.get('/prosiding/' + dataKetua.idUser),
              apiServer.get('/buku/' + dataKetua.idUser),
              apiServer.get('/penelitian/listByIdPeneliti/' + dataKetua.idUser),
              apiServer.get('/topik-unggulan/' + dataKetua.idUser),
            ]).then(axios.spread((response1, response2, response3, response4, response5, response6, response7, response8, response9, response10, response11) => {
              this.rekamJejak = response1.data
              this.timPenelitiNP = response2.data
              this.timPenelitiP = response3.data
              this.peneliti = response4.data
              this.isLoading = false
              this.usulanDetail = response5.data
              this.listJurnal = response6.data
              this.listHki = response7.data
              this.listProsiding = response8.data
              this.listBuku = response9.data
              this.listPenelitian = response10.data
              var organisasiTopik = response11.data
              this.peneliti.topikUnggulan = organisasiTopik.topikUnggulan
            })).catch(error => {
              console.log('There was an error:', error.response)
            })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      downloadItem (item) {
        apiServer.post('/output/download/' + item.idOutput)
          .then((resp) => {
            const ticket = resp.data.ticket
            window.location = fileUrl + '/download/output/' + item.idOutput + '?ticket=' + ticket
          })
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }

</style>
