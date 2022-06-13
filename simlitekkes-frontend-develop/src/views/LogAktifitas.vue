<template>
  <v-container fluid>
    <material-card
      color="green"
      title="Log Aktifitas"
    >
      <div v-if="hasAccess === false">
        <v-card>
          <v-row>
            <v-col class="align">
              <page403 />
            </v-col>
          </v-row>
        </v-card>
      </div>
      <div v-else>
        <v-row justify="center">
          <v-col cols="3">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="periode"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="periode"
                  label="Periode"
                  prepend-icon="mdi-calendar-month-outline"
                  readonly
                  v-on="on"
                />
              </template>
              <v-date-picker
                v-model="periode"
                type="month"
                no-title
                scrollable
                :max="new Date().toISOString().substr(0, 7)"
              >
                <v-spacer />
                <v-btn
                  text
                  color="primary"
                  @click="menu = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(periode)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="2">
            <v-btn
              color="success"
              class="white--text"
              @click="getData"
            >
              Lihat
            </v-btn>
          </v-col>
        </v-row>
        <v-data-table
          :headers="headers"
          :items="daftar"
          class="elevation-1"
          :loading="isLoading"
          loading-text="Loading... Please wait"
          :search="search"
          single-expand
          show-expand
          item-key="request"
        >
          <template v-slot:top>
            <v-toolbar dense>
              <v-toolbar-title>Daftar Log Aktivitas</v-toolbar-title>
            </v-toolbar>
            <v-toolbar
              flat
              class="mt-1"
            >
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Cari"
                single-line
                hide-details
                class="search"
              />
              <div class="flex-grow-1" />
            </v-toolbar>
          </template>
          <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length">
              {{ item.request }}
            </td>
          </template>
        </v-data-table>
      </div>
    </material-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { Page403 },
    data: () => ({
      headers: [
        { text: 'Waktu', width: '10%', value: 'waktuStr' },
        { text: 'Username', width: '15%', value: 'username' },
        { text: 'Method', width: '10%', value: 'method' },
        { text: 'Request', width: '50%', value: 'shortRequest' },
        { text: 'URL', value: 'url' },
        { text: '', value: 'data-table-expand' },
      ],
      daftar: [],
      isLoading: false,
      search: '',
      hasAccess: null,
      periode: new Date().toISOString().substr(0, 7),
      menu: false,
      expanded: [],
    }),

    created () {
      if (this.$store.state.user.role.roleCode === 'ADMIN') {
        this.hasAccess = true
        this.getData()
      } else {
        this.hasAccess = false
      }
    },

    methods: {
      getData () {
        console.log('periode:', this.periode)
        this.isLoading = true
        apiServer
          .get('/log-aktifitas/' + this.periode)
          .then(response => {
            this.daftar = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },

    },

  }
</script>

<style>

</style>
