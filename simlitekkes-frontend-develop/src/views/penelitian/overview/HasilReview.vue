<template>
  <v-card>
    <v-card-text>
      <v-simple-table>
        <template v-slot:default>
          <tbody>
            <tr>
              <td class="title">
                No
              </td>
              <td class="title" style="width:12%">
                Tab
              </td>
              <td class="title">
                Kriteria Penilaian
              </td>
            </tr>
            <tr
              v-for="(review, index) in listReview"
              :key="review.idPertanyaan"
              class="mb-4"
            >
              <td class="body-1">
                {{ index + 1 }}
              </td>
              <td class="body-1">{{ review.bagianUsulan }}
              </td>
              <td>
                <h2 class="body-1 font-weight-normal mt-4">
                  {{ review.pertanyaan }}
                </h2>
                <v-row>
                  <v-col>
                    <v-radio-group
                      v-model="listPostReview[index].idPilihanKriteriaPenilaian"
                      :error-messages="valJawaban(listPostReview[index].idPilihanKriteriaPenilaian, index)"
                      row
                      style="font-size: small"
                    >
                      <v-radio
                        v-for="jawab in review.listJawaban"
                        :key="jawab.idJawaban"
                        :value="jawab.idJawaban"
                        :label="jawab.jawaban"
                        class="mb-1"
                        :disabled="isNotReviewer || (!isNotReviewer && !isInReview)"
                      />
                    </v-radio-group>
                    <v-textarea
                      v-model="listPostReview[index].komentar"
                      label="Komentar"
                      :auto-grow="true"
                      rows="1"
                      :disabled="isNotReviewer || (!isNotReviewer && !isInReview)"
                    />
                  </v-col>
                </v-row>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
      <v-col
        class="text-right mb-2"
      >
        <v-col>
          <v-btn
            v-if="!isNotReviewer && isInReview"
            color="blue darken-1 white--text"
            @click="simpan"
          >
            Simpan Perubahan
          </v-btn>
        </v-col>
        <v-divider></v-divider>
        <div class="flex-grow-1" />
        <v-dialog
          v-model="dialog"
          max-width="600px"
        >
          <template v-slot:activator="{ on }">
            <v-col class="text-right">
              <v-card-actions>
                <div class="flex-grow-1" />
                <v-btn
                  v-if="!isNotReviewer && isInReview"
                  color="green darken-1 white--text"
                  v-on="on"
                  @click="decision = 'ACCEPTED'"
                >
                  Diterima
                </v-btn>
                <v-btn
                  v-if="!isNotReviewer && isInReview"
                  color="red darken-1 white--text"
                  v-on="on"
                  @click="decision = 'REJECTED'"
                >
                  Ditolak
                </v-btn>
                <v-btn
                  v-if="!isNotReviewer && isInReview && !isRevisionInReview"
                  color="yellow darken-2 white--text"
                  v-on="on"
                  @click="decision = 'NEED_REVISION'"
                >
                  Kembalikan Proposal
                </v-btn>
              </v-card-actions>
            </v-col>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">Konfirmasi Aksi</span>
            </v-card-title>
            <v-divider />
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col>
                    <p class="title">Anda yakin ingin {{decisionToString()}} proposal ini?</p>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="text-right">
                    <v-card-actions>
                      <div class="flex-grow-1" />
                      <v-btn
                        color="blue darken-1 white--text"
                        :loading="loading"
                        :disabled="loading"
                        @click="setApproval"
                      >
                        Kirim
                      </v-btn>
                      <v-btn
                        color="red darken-1 white--text"
                        @click="close"
                      >
                        Batal
                      </v-btn>
                    </v-card-actions>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions />
          </v-card>
        </v-dialog>
      </v-col>
    </v-card-text>
  </v-card>
</template>

<script>
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      dialog: false,
      tab: null,
      penelitian: {},
      isPlotted: null,
      isInReview: null,
      isRevisionInReview: null,
      listReview: [],
      listPostReview: [],
      count: 0,
      jawab: null,
      isNotReviewer: null,
      valError: null,
      isKirim: false,
      decision: null,
      statusTahapPenelitian: null,
      editedItem: {
        idPenelitian: null,
        idStatusTahapPenelitian: null,
        timestampUpdate: null,
        username: '',
      },
      loading: false,
    }),
    created () {
      this.isNotReviewer = this.$store.state.user.role.roleCode !== 'REVIEWER'
      this.getData()
    },
    methods: {
      getData () {
        this.listPostReview = []
        this.listReview = []
        apiServer
          .get('/penelitian/detailStatus/' + this.$route.params.id)
          .then(response => {
            this.penelitian = response.data
            if (this.penelitian != null) {
              this.isInReview = this.penelitian.status === 'IN_REVIEW' || this.penelitian.status === 'REVISION_IN_REVIEW'
              this.isRevisionInReview = this.penelitian.status === 'REVISION_IN_REVIEW'
            }
            apiServer
              .get('/hasil-review/' + this.penelitian.idSkemaPenelitian + '/' + this.$route.params.id)
              .then(response => {
                response.data.forEach(this.reformatReview)
              })
              .catch(error => {
                console.log('There was an error:', error.response) // Logs out the error
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      reformatReview (item) {
        var idPenelitian = this.$route.params.id
        if (item.bagianUsulan === 'Evaluasi Dokumen (Administrasi)' || item.bagianUsulan === 'Rekam Jejak Pengusul' || item.bagianUsulan === 'Tim Peneliti') {
          item.bagianUsulan = 'Identitas Usulan'
        } else if (item.bagianUsulan === 'Target Luaran') {
          item.bagianUsulan = 'Substansi Usulan'
        }

        if (this.listReview.length < 1) {
          this.listReview.push(
            { idPertanyaan: item.idPertanyaan, bagianUsulan: item.bagianUsulan, pertanyaan: item.pertanyaan, komentar: item.komentar, listJawaban: [{ jawaban: item.pilihan, isChoose: !!item.idJawabanDipilih, idJawaban: item.idJawaban }] }
          )
          this.listPostReview.push(
            { idPenelitian: idPenelitian, idKriteriaPenilaian: item.idPertanyaan, idPilihanKriteriaPenilaian: item.idJawabanDipilih, komentar: item.komentar, username: this.$store.state.user.username }
          )
        } else {
          var lastIndex = this.listReview.length - 1
          if (this.listReview[lastIndex].idPertanyaan === item.idPertanyaan) {
            this.listReview[lastIndex].listJawaban.push({ jawaban: item.pilihan, isChoose: !!item.idJawabanDipilih, idJawaban: item.idJawaban })
            if (item.idJawabanDipilih) {
              this.listPostReview[lastIndex].idPilihanKriteriaPenilaian = item.idJawabanDipilih
              this.listPostReview[lastIndex].komentar = item.komentar
            }
          } else {
            this.listReview.push(
              { idPertanyaan: item.idPertanyaan, bagianUsulan: item.bagianUsulan, pertanyaan: item.pertanyaan, komentar: item.komentar, listJawaban: [{ jawaban: item.pilihan, isChoose: !!item.idJawabanDipilih, idJawaban: item.idJawaban }] }
            )
            this.listPostReview.push(
              { idPenelitian: idPenelitian, idKriteriaPenilaian: item.idPertanyaan, idPilihanKriteriaPenilaian: item.idJawabanDipilih, komentar: item.komentar, username: this.$store.state.user.username }
            )
          }
        }
      },
      close () {
        this.dialog = false
      },
      simpan () {
        apiServer
          .get('/review/checkIsExist/' + this.$route.params.id)
          .then(response => {
            if (response.data) {
              apiServer
                .put('/hasil-review', this.listPostReview)
                .then(response => {
                  this.getData()
                })
                .catch(error => {
                  console.log('There was an error:', error.response) // Logs out the error
                })
            } else {
              apiServer
                .post('/hasil-review', this.listPostReview)
                .then(response => {
                  this.getData()
                })
                .catch(error => {
                  console.log('There was an error:', error.response) // Logs out the error
                })              }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      valJawaban (val, index) {
        if (index === 0) {
          this.valError = false
        }
        if (val === null) {
          this.valError = true
          if (this.isKirim) {
            return 'Tidak boleh kosong'
          } else {
            return ''
          }
        }
      },
      setApproval () {
        this.loading = true
        setTimeout(() => (this.loading = false), 6000)
        this.isKirim = true
        if (this.valError === false) {
          apiServer
            .get('/penelitian/getStatusBaru/' + this.penelitian.idStatusTahapPenelitian + '/' + this.$store.state.user.role.roleCode, {
              params: {
                kodeStatus: this.decision,
              },
            })
            .then(response => {
              console.log('statusnew', response.data)
              this.statusTahapPenelitian = response.data
              this.editedItem.idPenelitian = this.$route.params.id
              this.editedItem.idStatusTahapPenelitian = this.statusTahapPenelitian.id
              this.editedItem.timestampUpdate = new Date()
              this.editedItem.username = this.$store.state.user.username
              this.editedItem.isLast = true
              console.log('editedItem', this.editedItem)
              apiServer
                .put('/workflow-penelitian', this.editedItem)
                .then(response => {
                  apiServer
                    .post('/workflow-penelitian', this.editedItem)
                    .then(response => {
                      this.simpan()
                      this.close()
                    })
                    .catch(error => {
                      console.log('There was an error1:', error.response)
                    })
                })
                .catch(error => {
                  console.log('There was an error2:', error.response)
                })
            })
            .catch(error => {
              console.log('There was an error3:', error.response) // Logs out the error
            })
        } else {
          this.close()
        }
      },
      decisionToString () {
        switch (this.decision) {
          case 'ACCEPTED':
            return 'menerima'
          case 'REJECTED':
            return 'menolak'
          case 'NEED_REVISION':
            return 'memberikan status revisi'
          default:
            return ''
        }
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
