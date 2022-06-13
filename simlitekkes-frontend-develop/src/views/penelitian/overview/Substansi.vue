<template>
  <v-container fluid>
    <material-card
      color="blue"
      title="Substansi Usulan"
    >
      <v-form
        ref="form"
        v-model="form"
        @submit.prevent="save"
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
              :readonly="!isEdit"
              :rules="itemRules"
              outlined
              rounded
              no-resize
              required
            />
          </v-col>
        </v-row>
        <v-row
          v-if="isEdit"
        >
          <v-toolbar flat>
            <div class="flex-grow-1" />
            <v-btn
              type="submit"
              color="blue"
              class="mb-2 white--text"
            >
              Simpan
            </v-btn>
          </v-toolbar>
        </v-row>
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
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      dialog: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
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
      itemRules: [],
      isEdit: false,
      form: false,
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      apiServer
        .get('/validasi/h14w/' + this.$route.params.id + '/' + this.$store.state.user.userId)
        .then(response => {
          var draft = response.data
          if (this.$store.state.user.role.roleCode === 'DOSEN' && draft) {
            this.isEdit = true
          } else {
            this.isEdit = false
          }
          this.getData()
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },

    methods: {
      getData () {
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            this.$store.dispatch('setPenelitian', response.data)
            this.penelitian.id = this.$store.state.penelitian.id
            this.penelitian.substansi.ringkasan.content = this.$store.state.penelitian.ringkasan
            this.penelitian.substansi.latarBelakang.content = this.$store.state.penelitian.latarBelakang
            this.penelitian.substansi.tinjauanPustaka.content = this.$store.state.penelitian.tinjauanPustaka
            this.penelitian.substansi.metode.content = this.$store.state.penelitian.metode
            this.penelitian.substansi.daftarPustaka.content = this.$store.state.penelitian.daftarPustaka
            this.$refs.form.resetValidation()
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      save () {
        this.itemRules = [
          value =>
            !!value ||
            'Kolom ini wajib diisi!',
          value =>
            !value ||
            this.wordCount(value) <= 500 ||
            'Panjang Maksimal 500 Kata!',

        ]
        const self = this
        setTimeout(function () {
          if (self.$refs.form.validate()) {
            var formData = {
              id: self.penelitian.id,
              ringkasan: self.penelitian.substansi.ringkasan.content,
              latarBelakang: self.penelitian.substansi.latarBelakang.content,
              tinjauanPustaka: self.penelitian.substansi.tinjauanPustaka.content,
              metode: self.penelitian.substansi.metode.content,
              daftarPustaka: self.penelitian.substansi.daftarPustaka.content,
              username: self.$store.state.user.username,
            }

            apiServer
              .put('/penelitian', formData)
              .then(response => {
                self.getData()
                self.showSnackbar('success', 'Data Berhasil Diperbarui')
              })
              .catch(error => {
                console.log('There was an error:', error.response)
                self.showSnackbar('error', 'Terdapat Kesalahan')
              })
          } else {
            self.showSnackbar('error', 'Isian Form Tidak Memenuhi Syarat')
          }
        })
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      wordCount (str) {
        return str.split(' ')
          .filter(function (n) { return n !== '' })
          .length
      },
    },
  }
</script>
