<template>
  <v-container>
    <material-card v-if="hasAccess == false">
      <page403 />
    </material-card>
    <material-card
      color="blue"
      title="Detail Skema Penelitian"
      class="font-weight-bold"
      v-if="hasAccess"
    >
      <v-row v-if="snackbar">
        <v-snackbar
          v-model="snackbar"
          :color="snackbarColor"
          :timeout="2000"
          :top="true"
        >
          {{ snackbarText }}
          <v-btn
            dark
            text
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </v-snackbar>
      </v-row>
      <v-col>
        <v-simple-table class="ml-12 mr-12">
          <template v-slot:default>
            <tbody>
              <tr class="tabel-penelitian">
                <td>Kategori Penelitian</td>
                <td>{{ skema.namaKategori }}</td>
              </tr>
              <tr>
                <td>Nama Skema</td>
                <td>{{ skema.nama }}</td>
              </tr>
              <tr>
                <td>Tingkat Kesiapterapan Teknologi (TKT)</td>
                <td v-if="!isUbah">
                  {{ skema.tktString }}
                </td>
                <td v-if="isUbah">
                  <v-row align="center">
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-autocomplete
                        v-model="skema.tktMin"
                        :items="listAngka"
                        type="number"
                        hide-no-data
                        hide-selected
                      />
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1"
                    >
                      s/d
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-autocomplete
                        v-model="skema.tktMax"
                        :items="listAngka"
                        type="number"
                      />
                    </v-col>
                  </v-row>
                </td>
              </tr>
              <tr>
                <td>Jenis Penelitian</td>
                <td>{{ skema.jenisPenelitianNama }}</td>
              </tr>
              <tr>
                <td>Waktu Penelitian</td>
                <td>{{ skema.jangkaWaktuString }} tahun</td>
              </tr>
              <tr>
                <td>Pembiayaan Penelitian</td>
                <td v-if="!isUbah">
                  {{ skema.jumlahDanaString }}
                </td>
                <td v-if="isUbah">
                  <v-row align="center">
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-autocomplete
                        v-model="skema.jumlahDanaMin"
                        :items="listAngka"
                        type="number"
                        hide-no-data
                        hide-selected
                      />
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1"
                    >
                      s/d
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-autocomplete
                        v-model="skema.jumlahDanaMax"
                        :items="listAngka"
                        type="number"
                        hide-no-data
                        hide-selected
                      />
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1"
                    >
                      juta
                    </v-col>
                  </v-row>
                </td>
              </tr>
              <tr>
                <td>Luaran</td>
                <td>
                  <ol
                    type="a"
                    style="margin: 5px; margin-bottom: 25px"
                  >
                    <v-col
                      v-for="icon in luaran"
                      :key="icon"
                      style="margin-left: -3.5%; margin-bottom: -3%;"
                    >
                      <li style="margin-bottom: 10px">
                        {{ icon }}
                      </li>
                    </v-col>
                  </ol>
                </td>
              </tr>
              <tr>
                <td>Persyaratan Pengusul</td>
                <td>
                  <v-col
                    v-for="icon in persyaratanPenelitian"
                    :key="icon"
                    style="margin-left: -2%;"
                  >
                    {{ icon }}
                  </v-col>
                </td>
              </tr>
              <tr>
                <td>Jumlah Anggota Pengusul</td>
                <td v-if="!isUbah">
                  {{ skema.jumlahAnggotaString }}
                </td>
                <td v-if="isUbah">
                  <v-row align="center">
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-select
                        v-model="skema.jumlahPengusulMin"
                        :items="listAngka"
                        type="number"
                      />
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1"
                    >
                      s/d
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1.5"
                    >
                      <v-select
                        v-model="skema.jumlahPengusulMax"
                        :items="listAngka"
                        type="number"
                      />
                    </v-col>
                    <v-col
                      class="d-flex"
                      cols="2"
                      sm="1"
                    >
                      pengusul
                    </v-col>
                  </v-row>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
      <v-col
        cols="12"
        md="4"
      />
      <v-col
        v-if="!isUbah"
        cols="12"
        class="text-right mt-6"
      >
        <v-btn
          color="green"
          class="mb-2 white--text mr-2"
          @click="updateHalaman()"
        >
          Ubah
        </v-btn>
        <v-btn
          color="red"
          class="mb-2 white--text"
          @click="back()"
        >
          Kembali
        </v-btn>
      </v-col>
      <v-col
        v-if="isUbah"
        cols="12"
        class="text-right mt-6"
      >
        <v-btn
          color="green"
          class="mb-2 white--text mr-2"
          @click="updateData()"
        >
          Simpan
        </v-btn>
        <v-btn
          color="red"
          class="mb-2 white--text mr-2"
          @click="viewHalaman()"
        >
          Batal
        </v-btn>
      </v-col>
    </material-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {
    data: () => ({
      skema: {
        username: '',
      },
      persyaratanPenelitian: null,
      luaran: null,
      isUbah: false,
      listAngka: [],
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      hasAccess: null,
    }),

    components: {
      Page403: () => import('@/views/403.vue'),
    },

    created () {
      if (this.$store.state.user.organizationId != null) {
        this.getData()
        for (var i = 0; i < 10000; i++) {
          this.listAngka.push({ value: i, text: i })
        }
      } else {
        this.hasAccess = false
      }
    },

    methods: {
      getData () {
        apiServer
          .get('/validasi/checkUserIsFromPpsdm/' + this.$store.state.user.organizationId)
          .then(response => {
            this.hasAccess = response.data
            apiServer
              .get('/skemaPenelitian/' + this.$route.params.id)
              .then(response => {
                this.skema = response.data
                this.skema.username = this.$store.state.user.name
                console.log('skemaPenelitian by id', response.data) // For now, logs out the response
                if (this.skema.persyaratanPenelitian != null) {
                  this.persyaratanPenelitian = this.skema.persyaratanPenelitian.split(';')
                  this.luaran = this.skema.luaran.split('|||')
                }
                if (this.skema.jumlahDanaMin != null) {
                  this.skema.jumlahDanaMin = this.skema.jumlahDanaMin / 1000000
                }
                if (this.skema.jumlahDanaMax != null) {
                  this.skema.jumlahDanaMax = this.skema.jumlahDanaMax / 1000000
                }
              })
              .catch(error => {
                console.log('There was an error:', error.response) // Logs out the error
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      updateHalaman () {
        this.isUbah = true
      },
      viewHalaman () {
        this.isUbah = false
      },
      back () {
        this.$router.replace('/skema-penelitian')
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      updateData () {
        if (this.skema.jumlahDanaMin != null) {
          this.skema.jumlahDanaMin = this.skema.jumlahDanaMin * 1000000
        }
        if (this.skema.jumlahDanaMax != null) {
          this.skema.jumlahDanaMax = this.skema.jumlahDanaMax * 1000000
        }
        apiServer
          .put('/skemaPenelitian', this.skema)
          .then(response => {
            this.getData()
            this.showSnackbar('success', 'Data berhasil diubah')
            this.isUbah = false
            this.getData()
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat kesalahan input!')
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
