<template>
  <v-container
    class="pt-10 pb-10"
  >
    <v-card v-if="hasAccess == false">
      <page403 />
    </v-card>
    <v-card v-if="hasAccess">
      <v-card-title>Preview Usulan</v-card-title>
      <detail-usulan />
      <v-col
        class="text-right mb-2"
      >
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
                  v-if="isDosen"
                  color="blue darken-1 white--text"
                  :disabled="!((isDraft || isNeedRev) && hasAccess)"
                  v-on="on"
                  @click="ubahUsulan"
                >
                  {{isNeedRev ? 'Perbaiki Usulan' : 'Ubah'}}
                </v-btn>
                <v-btn
                  v-if="isReviewer && isPlotted"
                  color="blue darken-1 white--text"
                  v-on="on"
                  @click="mulaiReview"
                >
                  Mulai Review
                </v-btn>
                <v-btn
                  color="red darken-1 white--text"
                  @click="batal"
                >
                  Kembali
                </v-btn>
              </v-card-actions>
            </v-col>
          </template>
        </v-dialog>
      </v-col>
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
    </v-card>
    <v-card v-if="hasAccess && showReview" :key="identifier">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-header>
            <v-card-title>Penilaian Usulan Penelitian</v-card-title>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <hasil-review />
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
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
    </v-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'
  import axios from 'axios'

  export default {
    components: {
      DetailUsulan: () => import('@/views/penelitian/overview/DetailUsulan.vue'),
      HasilReview: () => import('@/views/penelitian/overview/HasilReview.vue'),
      Page403: () => import('@/views/403.vue'),
    },
    data: () => ({
      tab: null,
      penelitian: null,
      ketuaTimPeneliti: null,
      statusTahapPenelitian: null,
      editedItem: {
        idPenelitian: null,
        idStatusTahapPenelitian: null,
        timestampUpdate: null,
        username: '',
      },
      dialog: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      labelBtn: '',
      hasAccess: null,
      isDosen: null,
      isReviewer: null,
      isDraft: null,
      isPlotted: null,
      isNeedRev: null,
      showReview: null,
      identifier: null,
    }),
    created () {
      this.getData()
    },
    methods: {
      getData () {
        this.isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
        axios.all([
          apiServer.get('/timPeneliti/getKetuaTimPeneliti/' + this.$route.params.id),
          apiServer.get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId),
          apiServer.get('/penelitian/detailStatus/' + this.$route.params.id),
          apiServer.get('/validasi/isDosenAktif/' + this.$store.state.user.userId),
        ]).then(axios.spread((response, response1, response2, response3) => {
          console.log('response /timPeneliti/getKetuaTimPeneliti/:', response.data)
          this.penelitian = response2.data
          this.ketuaTimPeneliti = response.data
          var orgId = this.$store.state.user.organizationId == null ? 0 : this.$store.state.user.organizationId
          var isPoltekkes = response1.data
          var isDraft = this.penelitian.status === 'DRAFT'
          var isDraftRev = this.penelitian.status === 'DRAFT_REVISION'
          this.isNeedRev = this.penelitian.status === 'NEED_REVISION'
          this.isPlotted = this.penelitian.status === 'PLOTTED' || this.penelitian.status === 'REVISED'
          this.isDraft = isDraft || isDraftRev
          var isKetua = orgId === 0 || this.ketuaTimPeneliti.idOrganisasi === orgId
          var isAktif = response3.data
          this.isReviewer = false
          // console.log('isPoltekkes : ' + isPoltekkes + ', isDraft : ' + this.isDraft + ', isDosen : ' + this.isDosen + ', isKetua : ' + isKetua + ', isAktif/nidn!=null : ' + isAktif + ', isppsdm : ' + (this.$store.state.user.organizationId === 1))
          if (this.$store.state.user.role.roleCode === 'DOSEN') {
            this.hasAccess = isPoltekkes && this.isDosen && isKetua && isAktif
            console.log('isPoltekkes : ' + isPoltekkes + ', isDosen : ' + this.isDosen + ', isKetua : ' + isKetua + ', isAktif/nidn!=null : ' + isAktif)
          } else if (this.$store.state.user.role.roleCode === 'KAPUS_PPM') {
            this.hasAccess = !!(!isDraft && isKetua && isPoltekkes)
            console.log('isPoltekkes : ' + isPoltekkes + ', isDraft : ' + this.isDraft + ', isKetua : ' + isKetua)
          } else if (this.$store.state.user.role.roleCode === 'PENGELOLA_PUSAT') {
            this.hasAccess = !!(!isDraft && this.$store.state.user.organizationId === 1)
            console.log('isDraft : ' + this.isDraft + ', isppsdm : ' + (this.$store.state.user.organizationId === 1))
          } else if (this.$store.state.user.role.roleCode === 'REVIEWER') {
            this.isReviewer = true
            apiServer
              .get('/validasi/checkUserIsReviewer/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
              .then(response => {
                var isHasPenugasan = response.data
                console.log('isReviewer/has penugasan: ', response.data)
                apiServer
                  .get('validasi/I14R/' + this.$route.params.id + '/' + this.$store.state.user.userId)
                  .then(response1 => {
                    var isUsulanReviewer = response1.data
                    console.log('isUsulanByThisReviewer: ' + isUsulanReviewer)
                    if (isHasPenugasan && isUsulanReviewer) {
                      this.hasAccess = true
                    } else {
                      this.hasAccess = false
                    }
                  })
                  .catch(error => {
                    console.log('There was an error:', error.response) // Logs out the error
                  })
              })
              .catch(error => {
                console.log('There was an error:', error.response) // Logs out the error
                this.isLoading = false
              })
          }
          this.showReview = (!isDraft && this.penelitian.status !== 'PLOTTED' && this.penelitian.status !== 'SUBMITTED' && this.penelitian.status !== 'IN_REVIEW' && this.penelitian.status !== 'REVISION_IN_REVIEW') ||
            ((this.penelitian.status === 'IN_REVIEW' || this.penelitian.status === 'REVISION_IN_REVIEW') && this.isReviewer)
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      ubahUsulan () {
        if (this.isNeedRev === true) {
          this.addWorkFlow('ubahUsulan')
        } else if (this.isDraft && this.hasAccess) {
          this.$router.replace('/penelitian/' + this.$route.params.id + '/identitas-usulan')
        }
      },
      batal () {
        this.isDosen ? this.$router.replace('/usulan') : this.$router.replace('/penelitian/rekap')
      },
      close () {
        this.dialog = false
      },
      mulaiReview () {
        this.addWorkFlow('review')
      },
      addWorkFlow (action) {
        apiServer
          .get('/penelitian/getStatusBaru/' + this.penelitian.idStatusTahapPenelitian + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.statusTahapPenelitian = response.data
            this.labelBtn = this.statusTahapPenelitian.labelButton
            this.editedItem.idPenelitian = this.$route.params.id
            this.editedItem.idStatusTahapPenelitian = this.statusTahapPenelitian.id
            this.editedItem.timestampUpdate = new Date()
            this.editedItem.username = this.$store.state.user.username
            this.editedItem.isLast = true
            apiServer
              .put('/workflow-penelitian', this.editedItem)
              .then(response => {
                apiServer
                  .post('/workflow-penelitian', this.editedItem)
                  .then(response => {
                    if (action === 'ubahUsulan') this.$router.replace('/penelitian/' + this.$route.params.id + '/identitas-usulan')
                    else if (action === 'review') {
                      this.getData()
                      this.dialog = false
                    }
                    this.identifier = +new Date()
                  })
                  .catch(error => {
                    console.log('There was an error:', error.response)
                    this.showSnackbar('error', 'Error, please contact your administrator!')
                  })
              })
              .catch(error => {
                console.log('There was an error:', error.response)
                this.showSnackbar('error', 'Error, please contact your administrator!')
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
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
