<template>
  <v-container>
    <material-card
      color="blue"
      title="Substansi Usulan"
    >
      <v-row
        v-for="(item, key) in penelitian.substansi"
        :key="key"
      >
        <v-col>
          <v-textarea
            v-model="item.content"
            color="blue"
            :label="item.label"
            :readonly="true"
            outlined
            rounded
          />
        </v-col>
      </v-row>
    </material-card>
    <material-card
      color="blue"
      title="Luaran dan Target Usulan"
    >
      <v-row>
        <v-col>
          <v-data-table
            :headers="headers"
            :items="listLuaran"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="search"
          >
            <template v-slot:top>
              <v-toolbar
                flat
                class="mt-1"
              >
                <v-text-field
                  v-model="search"
                  append-icon="mdi-magnify"
                  label="Search"
                  single-line
                  hide-details
                  class="search"
                />
                <div class="flex-grow-1" />
              </v-toolbar>
            </template>
          </v-data-table>
        </v-col>
      </v-row>
    </material-card>
  </v-container>
</template>

<script>
  import axios from 'axios'
  import { apiServer } from '../../../../setup-axios'

  export default {
    data: () => ({
      search: '',
      idPenelitian: null,
      listLuaran: [],
      isLoading: true,
      headers: [
        { text: 'Tahun Target', value: 'tahunTarget', align: 'center' },
        { text: 'Jenis Output', value: 'nama' },
        { text: 'Wajib', value: 'wajib', align: 'center' },
        { text: 'Tambahan', value: 'tambahan', align: 'center' },
      ],
      penelitian: {
        id: '',
        substansi: {
          ringkasan: {
            label: 'Ringkasan',
            content: '',
          },
          latarBelakang: {
            label: 'Latar Belakang',
            content: '',
          },
          tinjauanPustaka: {
            label: 'Tinjauan Pustaka',
            content: '',
          },
          metode: {
            label: 'Metode Penelitian',
            content: '',
          },
          daftarPustaka: {
            label: 'Daftar Pustaka',
            content: '',
          },
        },
      },
    }),
    created () {
      this.getDataLuaran()
    },
    methods: {
      getDataLuaran () {
        this.isLoading = true
        this.idPenelitian = parseInt(this.$route.params.id)
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            this.$store.dispatch('setPenelitian', response.data)
            var idOrganisasi = this.$store.state.user.organizationId != null ? this.$store.state.user.organizationId : 0
            var role = this.$store.state.user.role.roleCode
            var idUser = this.$store.state.user.userId
            var temp = response.data
            this.penelitian.id = temp.id
            this.penelitian.substansi.ringkasan.content = temp.ringkasan
            this.penelitian.substansi.latarBelakang.content = temp.latarBelakang
            this.penelitian.substansi.tinjauanPustaka.content = temp.tinjauanPustaka
            this.penelitian.substansi.metode.content = temp.metode
            this.penelitian.substansi.daftarPustaka.content = temp.daftarPustaka
            axios.all([
              apiServer.get('/target-output-penelitian/' +
                this.idPenelitian + '/' +
                idOrganisasi + '/' +
                role + '/' +
                idUser),
            ])
              .then(axios.spread((response1) => {
                response1.data.map(value => {
                  value.wajib = value.isWajib ? 'V' : '-'
                  value.tambahan = value.isTambahan ? 'V' : '-'
                })
                console.log('target-output-penelitian', response1)
                this.listLuaran = response1.data
                this.isLoading = false
              }))
              .catch(error1 => {
                console.log('There was an error1:', error1.response) // Logs out the error
                this.isLoading = false
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
    },
  }
</script>
