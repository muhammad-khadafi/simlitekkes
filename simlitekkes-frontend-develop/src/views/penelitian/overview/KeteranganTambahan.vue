<template>
  <v-container fluid>
    <material-card
      color="blue"
      title="Keterangan Tambahan"
    >
      <v-form
        ref="form"
        v-model="form"
      >
        <v-container>
          <label>Rumpun Ilmu</label>
          <v-row>
            <v-col>
              <v-select
                v-model="editedItem.idRumpunIlmuLv1"
                :items="dropdownRumpunIlmuLv1"
                type="text"
                placeholder="Rumpun Ilmu Tingkat 1"
                item-value="id"
                item-text="nama"
                :rules="rules"
                @change="getDataRumpunIlmuLevel2"
              />
            </v-col>
            <v-col>
              <v-select
                v-model="editedItem.idRumpunIlmuLv2"
                :items="dropdownRumpunIlmuLv2"
                type="text"
                placeholder="Rumpun Ilmu Tingkat 2"
                item-value="id"
                item-text="nama"
                :rules="rules"
                :disabled="disabledRIL2"
                @change="getDataRumpunIlmuLevel3"
              />
            </v-col>
            <v-col>
              <v-select
                v-model="editedItem.idRumpunIlmu"
                :items="dropdownRumpunIlmuLv3"
                type="text"
                placeholder="Rumpun Ilmu Tingkat 3"
                item-value="id"
                item-text="nama"
                :rules="rules"
                :disabled="disabledRIL3"
              />
            </v-col>
          </v-row>
          <label>Tema Penelitian</label>
          <v-row>
            <v-col cols="6">
              <v-select
                v-model="editedItem.idTema"
                :items="dropdownTema"
                type="text"
                placeholder="Pilih Tema Penelitian"
                item-value="id"
                item-text="nama"
                :rules="rules"
                @change="getDataTopik"
              />
            </v-col>
          </v-row>
          <label>Topik Penelitian</label>
          <v-row>
            <v-col cols="6">
              <v-select
                v-model="editedItem.idTopik"
                :items="dropdownTopik"
                type="text"
                placeholder="Pilih Topik Penelitian"
                item-value="id"
                item-text="nama"
                :rules="rules"
                :disabled="disabledTopik"
              />
            </v-col>
          </v-row>
          <label>Tahun Pelaksanaan</label>
          <v-row>
            <v-col cols="3">
              <v-select
                v-model="editedItem.tahunMulaiPelaksanaan"
                :items="dropdownTahunMulaiPelaksanaan"
                type="text"
                placeholder="Tahun Mulai"
                item-value="value"
                :rules="rules"
                @change="getDataTahunSelesai"
              />
            </v-col>
            <v-col cols="3">
              <v-select
                v-model="editedItem.tahunSelesaiPelaksanaan"
                :items="dropdownTahunSelesaiPelaksanaan"
                type="text"
                placeholder="Tahun Selesai"
                item-value="value"
                :rules="rules"
                :disabled="disabledTSP"
              />
            </v-col>
          </v-row>
          <label>Lokasi Penelitian</label>
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="editedItem.lokasiPenelitian"
                type="text"
                placeholder="Lokasi Penelitian"
                item-value="value"
                :rules="rules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <label>Total RAB</label>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="totalRAB"
                type="text"
                item-value="value"
                readonly
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <label>Total Dana Mitra</label>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="totalKontribusiMitra"
                type="text"
                item-value="value"
                readonly
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <label>Daftar Barang dari Mitra</label>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-card>
                <v-list
                  dense
                  disabled
                >
                  <v-list-item-group>
                    <div
                      v-for="(value, i) in listRekapBarang"
                      :key="i"
                    >
                      <v-list-item>
                        <v-list-item-content>
                          <v-list-item-subtitle
                            class="text-wrap"
                            v-text="(i+1)+'. '+value"
                          />
                        </v-list-item-content>
                      </v-list-item>
                      <v-divider v-if="i != listRekapBarang.length-1" />
                    </div>
                  </v-list-item-group>
                </v-list>
              </v-card>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-right">
              <div class="flex-grow-1" />
              <v-btn
                color="blue darken-1"
                class="white--text"
                @click="save"
              >
                Simpan
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </material-card>
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
  </v-container>
</template>

<script>
  import axios from 'axios'
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      idPenelitian: null,
      idSkemaPenelitian: null,
      tahunUsulan: null,
      jangkaWaktuMin: null,
      jangkaWaktuMax: null,
      totalRAB: null,
      totalKontribusiMitra: null,
      listRekapBarang: null,
      isEdit: false,
      form: false,
      editedItem: {
        id: null,
        idRumpunIlmu: null,
        idTopik: null,
        tahunMulaiPelaksanaan: null,
        tahunSelesaiPelaksanaan: null,
        lokasiPenelitian: null,
        idRumpunIlmuLv1: null,
        idRumpunIlmuLv2: null,
        idTema: null
      },
      defaultItem: {
        id: null,
        idRumpunIlmu: null,
        idTopik: null,
        tahunMulaiPelaksanaan: null,
        tahunSelesaiPelaksanaan: null,
        lokasiPenelitian: '',
        idRumpunIlmuLv1: null,
        idRumpunIlmuLv2: null,
        idTema: null
      },
      dropdownRumpunIlmuLv1: [],
      dropdownRumpunIlmuLv2: [],
      dropdownRumpunIlmuLv3: [],
      dropdownTema: [],
      dropdownTopik: [],
      dropdownTahunMulaiPelaksanaan: [],
      dropdownTahunSelesaiPelaksanaan: [],
      disabledRIL2: true,
      disabledRIL3: true,
      disabledTopik: true,
      disabledTSP: true,
      rules: [
        value => !!value || 'Kolom Ini Harus Terisi!'
      ]
    }),

    watch: {
      dialog (val) {
        val || this.close()
      }
    },

    created () {
      if (this.$store.state.user.role.roleCode === 'DOSEN') {
        this.isEdit = true
      }
      this.getData()
    },

    methods: {
      getDataRumpunIlmuLevel2 (id) {
        apiServer
          .get('/rumpun-ilmu/2/' + id)
          .then(response => {
            this.editedItem.idRumpunIlmu = null
            this.editedItem.idRumpunIlmuLv2 = null
            this.dropdownRumpunIlmuLv2 = response.data
            this.dropdownRumpunIlmuLv3 = []
            this.disabledRIL2 = false
            this.disabledRIL3 = true
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      getDataRumpunIlmuLevel3 (id) {
        apiServer
          .get('/rumpun-ilmu/3/' + id)
          .then(response => {
            this.editedItem.idRumpunIlmu = null
            this.dropdownRumpunIlmuLv3 = response.data
            this.disabledRIL3 = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      getDataTopik (id) {
        apiServer
          .get('/topik/' + id)
          .then(response => {
            this.editedItem.idTopik = null
            this.dropdownTopik = response.data
            this.disabledTopik = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      getDataTahunSelesai (tahun) {
        tahun = parseInt(tahun)
        this.dropdownTahunSelesaiPelaksanaan = []
        this.editedItem.tahunSelesaiPelaksanaan = ''
        for (var i = this.jangkaWaktuMin; i <= this.jangkaWaktuMax; i++) {
          this.dropdownTahunSelesaiPelaksanaan.push((tahun + i) + '')
        }
        this.disabledTSP = false
      },
      getData () {
        this.idPenelitian = parseInt(this.$route.params.id)
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            this.$store.dispatch('setPenelitian', response.data)
            this.idSkemaPenelitian = response.data.idSkemaPenelitian
            this.tahunUsulan = parseInt(response.data.tahunUsulan)
            this.dropdownTahunMulaiPelaksanaan = [this.tahunUsulan + '', (this.tahunUsulan + 1) + '']
            axios
              .all([
                apiServer.get('/rumpun-ilmu/1/0'),
                apiServer.get('/tema'),
                apiServer.get('/skemaPenelitian/' + this.idSkemaPenelitian),
                apiServer.get('/rencana-anggaran/get-total-anggaran/' + this.$route.params.id),
                apiServer.get('/mitra-penelitian/rekap/' + this.$route.params.id)
              ])
              .then(axios.spread((response1, response2, response3, response4, response5) => {
                this.dropdownRumpunIlmuLv1 = response1.data
                this.dropdownTema = response2.data

                this.jangkaWaktuMin = response3.data.jangkaWaktuMin
                this.jangkaWaktuMax = response3.data.jangkaWaktuMax

                this.totalRAB = response4.data

                this.totalKontribusiMitra = response5.data.totalDanaMitra
                this.listRekapBarang = response5.data.rekapBarangMitra

                if (this.editedItem.tahunMulaiPelaksanaan !== null) {
                  var tahun = parseInt(this.editedItem.tahunMulaiPelaksanaan)
                  for (var i = this.jangkaWaktuMin; i <= this.jangkaWaktuMax; i++) {
                    this.dropdownTahunSelesaiPelaksanaan.push((tahun + i) + '')
                  }
                  this.disabledTSP = false
                }
              }))
              .catch(error => {
                console.log('There was an error:', error.response) // Logs out the error
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      save () {
        this.$refs.form.validate()
        if (this.form) {
          this.editedItem.id = parseInt(this.$route.params.id)
          this.editedItem.username = this.$store.state.user.username
          console.log('editedItem,', this.editedItem)
          this.updateData()
        } else {
          this.showSnackbar('error', 'Isian Form Tidak Memenuhi Syarat')
        }
      },
      updateData () {
        apiServer
          .put('/penelitian', this.editedItem)
          .then(response => {
            this.showSnackbar('success', 'Berhasil Mengubah Item!')
            // @TODO redirect ke halaman selanjutnya
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      }
    }
  }
</script>

<style lang="scss" scoped>
    // .search{
    //   margin-left: 20%;
    // }
</style>
