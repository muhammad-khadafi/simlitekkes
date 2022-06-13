<template>
  <v-container fluid>
    <h3>Jumlah Maksimal Peneliti : {{jmlMaxPeneliti}}</h3>
    <material-card color="blue" title="Identitas Pengusul - Anggota Peneliti">
      <v-data-table
        :headers="headersAnggotaPeneliti"
        :items="anggotaPenelitiPoltekkes"
        class="elevation-1"
        :loading="isLoadingPenelitiPoltekkes"
        loading-text="Loading... Please wait"
      >
        <template v-slot:top>
          <v-toolbar flat class="mt-1">
            <div class="flex-grow-1"></div>
            <v-dialog v-model="dialogPenelitiPoltekkes" max-width="1200px">
              <template v-slot:activator="{ on }">
                <v-btn color="blue" dark class="mb-2" v-on="on">Tambah Anggota</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">Tambah Anggota Peneliti</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-data-table
                          v-model="anggotaPenelitiPoltekkesSelected"
                          :headers="headersAnggotaPenelitiPoltekkesMaster"
                          :items="anggotaPenelitiPoltekkesMaster"
                          class="elevation-1"
                          :loading="isLoadingMasterPoltekkes"
                          loading-text="Loading... Please wait"
                          :search="searchMasterPenelitiPoltekkes"
                          show-select
                          item-key="nama"
                        >
                          <template v-slot:top>
                            <v-toolbar flat class="mt-1">
                              <v-text-field
                                v-model="searchMasterPenelitiPoltekkes"
                                append-icon="mdi-magnify"
                                label="Search"
                                single-line
                                hide-details
                                class="search"
                              ></v-text-field>
                              <div class="flex-grow-1"></div>
                            </v-toolbar>
                          </template>
                          <template v-slot:no-data></template>
                        </v-data-table>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          v-model="tugasPenelitian"
                          label="Tugas dalam penelitian"
                          required
                          @input="$v.tugasPenelitian.$touch()"
                          @blur="$v.tugasPenelitian.$touch()"
                          :error-messages="tugasPenelitianErrors"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="3">
                        <v-text-field
                          v-model="alokasiJam"
                          label="Alokasi jam per minggu"
                          type="number"
                          required
                          @input="$v.alokasiJam.$touch()"
                          @blur="$v.alokasiJam.$touch()"
                          :error-messages="alokasiJamErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="closePoltekkes">Batal</v-btn>
                  <v-btn color="blue darken-1" text @click="savePenelitiPoltekkes">Simpan</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
        <template v-slot:no-data></template>
      </v-data-table>
    </material-card>

    <material-card color="blue" title="Identitas Pengusul - Anggota Peneliti Non Poltekkes">
      <v-data-table
        :headers="headersAnggotaPeneliti"
        :items="anggotaPenelitiNonPoltekkes"
        class="elevation-1"
        :loading="isLoadingPenelitiNonPoltekkes"
        loading-text="Loading... Please wait"
      >
        <template v-slot:top>
          <v-toolbar flat class="mt-1">
            <div class="flex-grow-1"></div>
            <v-dialog v-model="dialogAnggotaTidakTerdaftar" max-width="1000px">
              <template v-slot:activator="{ on }">
                <v-btn color="blue" dark class="mb-2 mr-3" v-on="on">Tambah Anggota Tidak Terdaftar</v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">Tambah Peneliti Non Poltekkes</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="6">
                        <v-text-field
                          v-model="anggotaPenelitiTidakTerdaftar.nama"
                          label="Nama Lengkap dan Gelar *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.nama.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.nama.$touch()"
                          :error-messages="namaErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="6">
                        <v-text-field
                          v-model="anggotaPenelitiTidakTerdaftar.email"
                          label="Email *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.email.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.email.$touch()"
                          :error-messages="emailErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="6">
                        <v-text-field
                          v-model="anggotaPenelitiTidakTerdaftar.noKtp"
                          label="Nomor Identitas (KTP/Paspor) *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.noKtp.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.noKtp.$touch()"
                          :error-messages="noKtpErrors"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          v-model="anggotaPenelitiTidakTerdaftar.noHp"
                          label="Nomor HP *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.noHp.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.noHp.$touch()"
                          :error-messages="noHpErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="6">
                        <v-select
                          v-model="anggotaPenelitiTidakTerdaftar.idOrganisasi"
                          :items="instansi"
                          item-text="nama"
                          item-value="id"
                          label="Instansi *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.idOrganisasi.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.idOrganisasi.$touch()"
                          :error-messages="instansiErrors"
                        ></v-select>
                      </v-col>

                      <v-col cols="3">
                        <v-select
                          v-model="anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir"
                          :items="kualifikasi"
                          item-text="label"
                          item-value="value"
                          label="Kualifikasi *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir.$touch()"
                          :error-messages="kualifikasiErrors"
                        ></v-select>
                      </v-col>

                      <v-col cols="6">
                        <v-select
                          v-model="anggotaPenelitiTidakTerdaftar.idBidangKeahlian"
                          :items="bidangKeahlian"
                          item-text="nama"
                          item-value="id"
                          label="Bidang Keahlian *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.idBidangKeahlian.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.idBidangKeahlian.$touch()"
                          :error-messages="bidangKeahlianErrors"
                        ></v-select>
                      </v-col>

                      <v-col cols="6"></v-col>

                      <v-col cols="6">
                        <v-text-field
                          v-model="anggotaPenelitiTidakTerdaftar.tugasPenelitian"
                          label="Tugas Dalam Penelitian *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.tugasPenelitian.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.tugasPenelitian.$touch()"
                          :error-messages="tugasPenelitian2Errors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="6">
                        <v-text-field
                          type="number"
                          v-model="anggotaPenelitiTidakTerdaftar.alokasiJam"
                          label="Alokasi Jam Per Minggu *"
                          required
                          @input="$v.anggotaPenelitiTidakTerdaftar.alokasiJam.$touch()"
                          @blur="$v.anggotaPenelitiTidakTerdaftar.alokasiJam.$touch()"
                          :error-messages="alokasiJam2Errors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="closeAnggotaTidakTerdaftar">Batal</v-btn>
                  <v-btn color="blue darken-1" text @click="saveAnggotaTidakTerdaftar">Simpan</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialogPenelitiNonPoltekkes" max-width="1200px">
              <template v-slot:activator="{ on }">
                <v-btn color="blue" dark class="mb-2" v-on="on">Tambah Anggota Terdaftar</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">Tambah Anggota Peneliti Non Poltekkes</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-data-table
                          v-model="anggotaPenelitiNonPoltekkesSelected"
                          :headers="headersAnggotaPenelitiPoltekkesMaster"
                          :items="anggotaPenelitiNonPoltekkesMaster"
                          class="elevation-1"
                          :loading="isLoadingMasterNonPoltekkes"
                          loading-text="Loading... Please wait"
                          :search="searchMasterPenelitiNonPoltekkes"
                          show-select
                          item-key="nama"
                        >
                          <template v-slot:top>
                            <v-toolbar flat class="mt-1">
                              <v-text-field
                                v-model="searchMasterPenelitiNonPoltekkes"
                                append-icon="mdi-magnify"
                                label="Search"
                                single-line
                                hide-details
                                class="search"
                              ></v-text-field>
                              <div class="flex-grow-1"></div>
                            </v-toolbar>
                          </template>
                          <template v-slot:no-data></template>
                        </v-data-table>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          v-model="tugasPenelitian"
                          label="Tugas dalam penelitian"
                          required
                          @input="$v.tugasPenelitian.$touch()"
                          @blur="$v.tugasPenelitian.$touch()"
                          :error-messages="tugasPenelitianErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="3">
                        <v-text-field
                          v-model="alokasiJam"
                          label="Alokasi jam per minggu"
                          type="number"
                          required
                          @input="$v.alokasiJam.$touch()"
                          @blur="$v.alokasiJam.$touch()"
                          :error-messages="alokasiJamErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="closeNonPoltekkes">Batal</v-btn>
                  <v-btn color="blue darken-1" text @click="savePenelitiNonPoltekkes">Simpan</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
        <template v-slot:no-data></template>
      </v-data-table>
    </material-card>

    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import { apiServer } from "../../../setup-axios";
import {
  required,
  maxValue,
  minValue,
  between
} from "vuelidate/lib/validators";

export default {
  data: () => ({
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    searchMasterPenelitiPoltekkes: "",
    searchMasterPenelitiNonPoltekkes: "",
    dialogPenelitiPoltekkes: false,
    dialogPenelitiNonPoltekkes: false,
    dialogAnggotaTidakTerdaftar: false,
    isLoadingPenelitiPoltekkes: true,
    isLoadingPenelitiNonPoltekkes: true,
    isLoadingMasterPoltekkes: true,
    isLoadingMasterNonPoltekkes: true,
    jmlMaxPeneliti: "",
    tugasPenelitian: "",
    alokasiJam: "",
    anggotaPenelitiPoltekkes: [],
    anggotaPenelitiPoltekkesSelected: [],
    anggotaPenelitiNonPoltekkes: [],
    anggotaPenelitiNonPoltekkesSelected: [],
    anggotaPenelitiPoltekkesMaster: [],
    anggotaPenelitiNonPoltekkesMaster: [],
    headersAnggotaPeneliti: [
      { text: "Nama", value: "nama" },
      { text: "Institusi", value: "namaInstitusi" },
      { text: "Bidang Keahlian", value: "namaBidang" },
      { text: "Tugas Penelitian", value: "tugasPenelitian" },
      { text: "Hapus", value: "action", sortable: false }
    ],
    headersAnggotaPenelitiPoltekkesMaster: [
      { text: "Nama", value: "nama" },
      { text: "Institusi", value: "namaInstitusi" },
      { text: "Program Studi", value: "programStudi" },
      { text: "Bidang Keahlian", value: "namaBidang" }
    ],
    instansi: [],
    bidangKeahlian: [],
    kualifikasi: [],
    anggotaPenelitiTidakTerdaftar: {
      nama: "",
      email: "",
      noKtp: "",
      noHp: "",
      idOrganisasi: "",
      jenjangPendidikanTerakhir: "",
      idBidangKeahlian: "",
      tugasPenelitian: "",
      alokasiJam: ""
    },
    anggotaPenelitiTidakTerdaftarReset: {
      nama: "",
      email: "",
      noKtp: "",
      noHp: "",
      idOrganisasi: "",
      jenjangPendidikanTerakhir: "",
      idBidangKeahlian: "",
      tugasPenelitian: "",
      alokasiJam: ""
    },
    allPeneliti: []
  }),

  validations: {
    tugasPenelitian: {
      required
    },
    alokasiJam: {
      required,
      between: between(0.0, 168.0)
    },
    anggotaPenelitiTidakTerdaftar: {
      nama: {
        required,
        async isUnique(value) {
          if (value === "") return true;

          for (var index in this.allPeneliti) {
            if (
              this.allPeneliti[index].nama.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      email: {
        required
      },
      noKtp: {
        required
      },
      noHp: {
        required
      },
      idOrganisasi: {
        required
      },
      jenjangPendidikanTerakhir: {
        required
      },
      idBidangKeahlian: {
        required
      },
      tugasPenelitian: {
        required
      },
      alokasiJam: {
        required,
        between: between(0.0, 168.0)
      }
    }
  },

  computed: {
    tugasPenelitianErrors() {
      const errors = [];
      if (!this.$v.tugasPenelitian.$dirty) return errors;
      !this.$v.tugasPenelitian.required &&
        errors.push("Tugas penelitian harus di isi");
      return errors;
    },
    alokasiJamErrors() {
      console.log("jam error..", this.$v.alokasiJam);
      const errors = [];
      if (!this.$v.alokasiJam.$dirty) return errors;
      !this.$v.alokasiJam.required &&
        errors.push("Alokasi jam per minggu harus di isi");
      !this.$v.alokasiJam.between &&
        errors.push("Alokasi jam per minggu harus Diantara 0 - 168 jam");
      return errors;
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.nama.$dirty) return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.nama.required &&
        errors.push("nama harus di isi");
      !this.$v.anggotaPenelitiTidakTerdaftar.nama.isUnique &&
        errors.push("peneliti sudah terdaftar");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.email.$dirty) return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.email.required &&
        errors.push("email harus di isi");
      return errors;
    },
    noKtpErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.noKtp.$dirty) return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.noKtp.required &&
        errors.push("No KTP/Paspor harus di isi");
      return errors;
    },
    noHpErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.noHp.$dirty) return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.noHp.required &&
        errors.push("No HP harus di isi");
      return errors;
    },
    instansiErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.idOrganisasi.$dirty)
        return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.idOrganisasi.required &&
        errors.push("Instansi harus di isi");
      return errors;
    },
    kualifikasiErrors() {
      const errors = [];
      if (
        !this.$v.anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir.$dirty
      )
        return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir
        .required && errors.push("Kualifikasi harus di isi");
      return errors;
    },
    bidangKeahlianErrors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.idBidangKeahlian.$dirty)
        return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.idBidangKeahlian.required &&
        errors.push("Bidang Keahlian harus di isi");
      return errors;
    },
    tugasPenelitian2Errors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.tugasPenelitian.$dirty)
        return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.tugasPenelitian.required &&
        errors.push("Tugas penelitian harus di isi");
      return errors;
    },
    alokasiJam2Errors() {
      const errors = [];
      if (!this.$v.anggotaPenelitiTidakTerdaftar.alokasiJam.$dirty)
        return errors;
      !this.$v.anggotaPenelitiTidakTerdaftar.alokasiJam.required &&
        errors.push("Alokasi jam per minggu harus di isi");
      !this.$v.anggotaPenelitiTidakTerdaftar.alokasiJam.between &&
        errors.push("Alokasi jam per minggu harus Diantara 0 - 168 jam");
      return errors;
    }
  },

  created() {
    this.getAllPeneliti();
    this.getMaxPeneliti();
    this.getAnggotaPenelitiPoltekkes();
    this.getAnggotaPenelitiNonPoltekkes();
    this.getInstansi();
    this.getKualifikasi();
    this.getBidangKeahlian();
  },

  watch: {
    dialogPenelitiPoltekkes(val) {
      val || this.closePoltekkes();
      if (val == true) {
        this.getAnggotaPenelitiPoltekkesMaster();
      }
    },
    dialogPenelitiNonPoltekkes(val) {
      val || this.closeNonPoltekkes();
      if (val == true) {
        this.getAnggotaPenelitiNonPoltekkesMaster();
      }
    },
    dialogAnggotaTidakTerdaftar(val) {
      val || this.closeAnggotaTidakTerdaftar();
    }
  },

  methods: {
    getAllPeneliti() {
      apiServer
        .get("/peneliti")
        .then(response => {
          this.allPeneliti = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getMaxPeneliti() {
      apiServer
        .get("/skema-penelitian-by-penelitian/" + this.$route.params.id)
        .then(response => {
          this.jmlMaxPeneliti = response.data.jumlahPengusulMax;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getInstansi() {
      apiServer
        .get("/organisasi-non-poltekkes")
        .then(response => {
          this.instansi = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getBidangKeahlian() {
      apiServer
        .get("/bidang-keahlian")
        .then(response => {
          this.bidangKeahlian = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getKualifikasi() {
      var s1 = { label: "S1", value: "Sarjana" };
      var s2 = { label: "S2", value: "Magister" };
      var s3 = { label: "S3", value: "Doktor" };
      this.kualifikasi.push(s1);
      this.kualifikasi.push(s2);
      this.kualifikasi.push(s3);
    },
    getAnggotaPenelitiPoltekkesMaster() {
      apiServer
        .get("/anggota-peneliti/" + true + "/" + this.$route.params.id)
        .then(response => {
          this.anggotaPenelitiPoltekkesMaster = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      this.isLoadingMasterPoltekkes = false;
    },
    getAnggotaPenelitiNonPoltekkesMaster() {
      apiServer
        .get("/anggota-peneliti/" + false + "/" + this.$route.params.id)
        .then(response => {
          this.anggotaPenelitiNonPoltekkesMaster = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      this.isLoadingMasterNonPoltekkes = false;
    },
    getAnggotaPenelitiPoltekkes() {
      apiServer
        .get("/tim-peneliti/" + this.$route.params.id + "/" + true)
        .then(response => {
          this.anggotaPenelitiPoltekkes = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      this.isLoadingPenelitiPoltekkes = false;
    },
    getAnggotaPenelitiNonPoltekkes() {
      apiServer
        .get("/tim-peneliti/" + this.$route.params.id + "/" + false)
        .then(response => {
          this.anggotaPenelitiNonPoltekkes = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      this.isLoadingPenelitiNonPoltekkes = false;
    },
    closePoltekkes() {
      this.getAllPeneliti();
      this.dialogPenelitiPoltekkes = false;
      this.anggotaPenelitiPoltekkesSelected = [];
      this.tugasPenelitian = "";
      this.alokasiJam = "";
      this.anggotaPenelitiTidakTerdaftar = Object.assign(
        {},
        this.anggotaPenelitiTidakTerdaftarReset
      );
      this.$v.$reset();
    },
    closeNonPoltekkes() {
      this.getAllPeneliti();
      this.dialogPenelitiNonPoltekkes = false;
      this.anggotaPenelitiNonPoltekkesSelected = [];
      this.tugasPenelitian = "";
      this.alokasiJam = "";
      this.anggotaPenelitiTidakTerdaftar = Object.assign(
        {},
        this.anggotaPenelitiTidakTerdaftarReset
      );
      this.$v.$reset();
    },
    closeAnggotaTidakTerdaftar() {
      this.getAllPeneliti();
      this.dialogAnggotaTidakTerdaftar = false;
      this.anggotaPenelitiTidakTerdaftar = Object.assign(
        {},
        this.anggotaPenelitiTidakTerdaftarReset
      );
      this.tugasPenelitian = "";
      this.alokasiJam = "";
      this.$v.$reset();
    },
    checkMaxPeneliti() {
      var result = true;
      var totalPeneliti =
        this.anggotaPenelitiPoltekkesSelected.length +
        this.anggotaPenelitiNonPoltekkesSelected.length +
        this.anggotaPenelitiPoltekkes.length +
        this.anggotaPenelitiNonPoltekkes.length;

      if (totalPeneliti > this.jmlMaxPeneliti) {
        result = false;
      }
      return result;
    },
    checkMaxPeneliti2() {
      var result = true;
      var totalPeneliti =
        this.anggotaPenelitiPoltekkes.length +
        this.anggotaPenelitiNonPoltekkes.length +
        1;

      if (totalPeneliti > this.jmlMaxPeneliti) {
        result = false;
      }
      return result;
    },
    savePenelitiPoltekkes() {
      this.$v.$touch();
      if (!this.$v.alokasiJam.$invalid && !this.$v.tugasPenelitian.$invalid) {
        if (this.checkMaxPeneliti()) {
          apiServer
            .post(
              "/anggota-peneliti/" +
                this.$route.params.id +
                "/" +
                this.tugasPenelitian +
                "/" +
                this.alokasiJam,
              this.anggotaPenelitiPoltekkesSelected
            )
            .then(response => {
              this.getAnggotaPenelitiPoltekkes();
              this.showSnackbar(
                "success",
                "Berhasil menambahkan anggota peneliti"
              );
            })
            .catch(error => {
              console.log("There was an error:", error.response); // Logs out the error
            });
          this.closePoltekkes();
        } else {
          this.showSnackbar(
            "error",
            "Jumlah peneliti yang ditambahkan melebihi batas maksimal peneliti"
          );
        }
      }
    },
    savePenelitiNonPoltekkes() {
      this.$v.$touch();
      if (!this.$v.alokasiJam.$invalid && !this.$v.tugasPenelitian.$invalid) {
        console.log("lolos validasi");
        if (this.checkMaxPeneliti()) {
          apiServer
            .post(
              "/anggota-peneliti/" +
                this.$route.params.id +
                "/" +
                this.tugasPenelitian +
                "/" +
                this.alokasiJam,
              this.anggotaPenelitiNonPoltekkesSelected
            )
            .then(response => {
              this.getAnggotaPenelitiNonPoltekkes();
              this.showSnackbar(
                "success",
                "Berhasil menambahkan anggota peneliti"
              );
            })
            .catch(error => {
              console.log("There was an error:", error.response); // Logs out the error
            });
          this.closeNonPoltekkes();
        } else {
          this.showSnackbar(
            "error",
            "Jumlah peneliti yang ditambahkan melebihi batas maksimal peneliti"
          );
        }
      }
    },
    saveAnggotaTidakTerdaftar() {
      this.$v.$touch();
      if (!this.$v.anggotaPenelitiTidakTerdaftar.$invalid) {
        if (this.checkMaxPeneliti2()) {
          apiServer
            .post(
              "/anggota-peneliti-tidak-terdaftar/" + this.$route.params.id,
              this.anggotaPenelitiTidakTerdaftar
            )
            .then(response => {
              this.getAnggotaPenelitiNonPoltekkes();
              this.showSnackbar(
                "success",
                "Berhasil menambahkan anggota peneliti"
              );
            })
            .catch(error => {
              this.showSnackbar("error", "Error, silakan kontak administrator");
            });
          this.closeAnggotaTidakTerdaftar();
        } else {
          this.showSnackbar(
            "error",
            "Jumlah peneliti yang ditambahkan melebihi batas maksimal peneliti"
          );
        }
      }
    },
    setDefaultValidation() {
      this.alokasiJam = 0;
      this.tugasPenelitian = "-1";
    },
    setDefaultValidation2() {
      this.anggotaPenelitiTidakTerdaftar.nama = "-1";
      this.anggotaPenelitiTidakTerdaftar.email = "-1";
      this.anggotaPenelitiTidakTerdaftar.noKtp = "-1";
      this.anggotaPenelitiTidakTerdaftar.noHp = "-1";
      this.anggotaPenelitiTidakTerdaftar.idOrganisasi = "-1";
      this.anggotaPenelitiTidakTerdaftar.jenjangPendidikanTerakhir = "-1";
      this.anggotaPenelitiTidakTerdaftar.idBidangKeahlian = "-1";
      this.anggotaPenelitiTidakTerdaftar.tugasPenelitian = "-1";
      this.anggotaPenelitiTidakTerdaftar.alokasiJam = 0;
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },

    deleteData(id) {
      apiServer
        .delete("/anggota-peneliti/" + id)
        .then(response => {
          this.getAnggotaPenelitiPoltekkes();
          this.getAnggotaPenelitiNonPoltekkes();
          this.showSnackbar("success", "Data berhasil dihapus");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },

    deleteItem(item) {
      confirm("Apakah anda yakin akan menghapus data ini?") &&
        this.deleteData(item.id);
    }
  }
};
</script>

<style>
</style>