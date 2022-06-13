<template>
  <v-container fluid>
    <div v-if="isDosen">
      <div v-if="!anggotaPenelitiView">
        <v-row>
          <v-col cols="2"></v-col>
          <v-col cols="7">
            <v-text-field label="Judul" v-model="editedItem.judul" :disabled="!editJudul"></v-text-field>
          </v-col>
          <v-col cols="1" sm="3">
            <v-btn text color="blue" @click="ubahJudul()">
              {{editJudul === false ? 'ubah' : 'simpan'}}
            </v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2"></v-col>
          <v-col cols="4">
            <v-select
              :items="listJenisTkt"
              item-text="nama"
              item-value="id"
              label="Jenis TKT *"
              v-model="idJenisTktSelected"
              required
              @input="$v.idJenisTktSelected.$touch()"
              @blur="$v.idJenisTktSelected.$touch()"
              :error-messages="jenisTktErrors"
            ></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2"></v-col>
          <v-col cols="4">
            <v-text-field
              label="TKT Saat Ini"
              v-model="tktSaatIni"
              :disabled="true"
              required
              :error-messages="tktSaatIniErrors"
            ></v-text-field>
          </v-col>
          <v-col cols="2">
            <v-btn class="mt-4" color="blue" @click="hitungTkt()">Ukur</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2"></v-col>
          <v-col cols="4">
            <v-text-field
              label="Target Akhir TKT"
              v-model="tktTarget"
              :disabled="true"
              required
              :error-messages="tktTargetErrors"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-divider class="mb-7 mt-7"></v-divider>

        <div v-show="tktSaatIni != null">
          <v-row class="mb-5">
            <v-col cols="2"></v-col>
            <v-col cols="4">
              <h2>Identitas Pengusul</h2>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2"></v-col>
            <v-col cols="8">
              <v-text-field
                v-model="topikOrganisasi.topikUnggulan"
                label="Topik Unggulan"
                disabled
              ></v-text-field>
            </v-col>
            <v-col cols="4">
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="2"></v-col>
            <v-col cols="4">
              <v-select
                :items="listIlmuLv1"
                item-text="nama"
                item-value="id"
                label="Rumpun Ilmu * (Level 1)"
                @change="getIlmuLevel2()"
                v-model="editedItem.idIlmuLv1"
                required
                @input="$v.editedItem.idIlmuLv1.$touch()"
                @blur="$v.editedItem.idIlmuLv1.$touch()"
                :error-messages="rumpunIlmuLv1Errors"
              ></v-select>
            </v-col>
            <v-col cols="4">
              <v-select
                :items="listTema"
                item-text="nama"
                item-value="id"
                label="Tema Penelitian *"
                @change="getTopik()"
                v-model="editedItem.idTema"
                required
                @input="$v.editedItem.idTema.$touch()"
                @blur="$v.editedItem.idTema.$touch()"
                :error-messages="temaErrors"
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2"></v-col>
            <v-col cols="4">
              <v-select
                :items="listIlmuLv2"
                v-show="editedItem.idIlmuLv1 != '' && typeof editedItem.idIlmuLv1 !== 'undefined'
            && editedItem.idIlmuLv1 != null"
                item-text="nama"
                item-value="id"
                label="Rumpun Ilmu * (Level 2)"
                v-model="editedItem.idIlmuLv2"
                @change="getIlmuLevel3()"
                required
                @input="$v.editedItem.idIlmuLv2.$touch()"
                @blur="$v.editedItem.idIlmuLv2.$touch()"
                :error-messages="rumpunIlmuLv2Errors"
              ></v-select>
            </v-col>
            <v-col cols="4">
              <v-select
                :items="listTopik"
                item-text="nama"
                item-value="id"
                label="Topik Penelitian *"
                v-model="editedItem.idTopik"
                required
                @input="$v.editedItem.idTopik.$touch()"
                @blur="$v.editedItem.idTopik.$touch()"
                :error-messages="topikErrors"
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2"></v-col>
            <v-col cols="4">
              <v-select
                :items="listIlmuLv3"
                v-show="editedItem.idIlmuLv2 != '' && typeof editedItem.idIlmuLv2 !== 'undefined'
            && editedItem.idIlmuLv2 != null"
                item-text="nama"
                item-value="id"
                label="Rumpun Ilmu * (Level 3)"
                v-model="editedItem.idIlmuLv3"
                required
                @input="$v.editedItem.idIlmuLv3.$touch()"
                @blur="$v.editedItem.idIlmuLv3.$touch()"
                :error-messages="rumpunIlmuLv3Errors"
              ></v-select>
            </v-col>
            <v-col cols="2">
              <v-text-field
                label="Tahun Pelaksanaan"
                v-model="editedItem.tahunMulaiPelaksanaan"
                required
                @input="$v.editedItem.tahunMulaiPelaksanaan.$touch()"
                @blur="$v.editedItem.tahunMulaiPelaksanaan.$touch()"
                :error-messages="tahunMulaiPelaksanaanErrors"
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <v-select
                :items="listLamaKegiatan"
                item-text="lamaKegiatan"
                item-value="lamaKegiatan"
                label="Lama Kegiatan"
                v-model="editedItem.lamaKegiatan"
                required
                @input="$v.editedItem.lamaKegiatan.$touch()"
                @blur="$v.editedItem.lamaKegiatan.$touch()"
                :error-messages="lamaKegiatanErrors"
              ></v-select>
            </v-col>
          </v-row>

          <v-row class="mb-8">
            <v-col cols="8"></v-col>
            <v-col cols="2">
              <v-btn color="blue darken-1 mb-3" class="float-right" @click="submitAll()">Simpan</v-btn>
            </v-col>
          </v-row>
        </div>

        <v-dialog v-model="dialog" max-width="1000px">
          <v-card>
            <v-card-title>
              <span class="headline">Perhitungan TKT</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="Definisi/Status TKT"
                      v-model="tktData.definisi"
                      :disabled="true"
                      class="font-weight-bold"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" class="text-right">
                    <h3>Level TKT {{activeLvl}}</h3>
                  </v-col>
                  <v-divider></v-divider>
                  <v-col cols="12">
                    <v-data-table
                      :headers="headers"
                      :items="tktData.indikatorTktList"
                      class="elevation-1"
                      :loading="isLoading"
                      loading-text="Loading... Please wait"
                      height="300px"
                      fixed-header
                      hide-default-footer
                      :items-per-page="100"
                    >
                      <template v-slot:item.percentage="{ item }">
                        <v-select
                          :items="percentageList"
                          item-text="label"
                          item-value="value"
                          v-model="item.percentage"
                        ></v-select>
                      </template>
                    </v-data-table>
                  </v-col>
                  <v-col cols="12" class="text-right">
                    <v-btn color="blue darken-1" text @click="averageScoreProcess()">Hitung</v-btn>
                  </v-col>
                  <v-col cols="12" class="text-center">
                    <h3>{{tktMessage}}</h3>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1 mb-3"></div>
              <v-btn
                v-show="isFlowFinished == 1"
                color="blue darken-1 mb-3"
                @click="submit()"
              >Simpan</v-btn>
              <v-btn
                v-show="isFlowFinished == 0"
                color="blue darken-1 mb-3"
                @click="nextLevel()"
              >Lanjutkan</v-btn>
              <v-btn color="blue darken-1 mb-3 mr-6" @click="close()">Batal</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <div v-if="anggotaPenelitiView">
        <AnggotaPeneliti />
      </div>
    </div>
    <div v-if="!isDosen">
      <material-card color="blue" title="Identitas Usulan Penelitian - Ketua">
        <v-container>
          <v-card>
            <v-card-text>Nama: {{dataKetua.namaKetua}}</v-card-text>
            <v-card-text>Institusi: {{dataKetua.institusi}}</v-card-text>
          </v-card>
        </v-container>
      </material-card>
      <material-card color="blue" title="Identitas Usulan Penelitian - Anggota Peneliti">
        <v-container>
          <v-card v-show="hasAnggotaP" v-for="(anggotaP, index) in dataAnggotaP" :key="index">
            <v-card-text>Anggota {{indext+1}}.</v-card-text>
            <v-card-text>{{anggotaP.nama}}</v-card-text>
            <v-card-text>{{anggotaP.namaInstitusi}} - {{anggotaP.peranPeneliti}}</v-card-text>
            <v-card-text>{{anggotaP.tugasPenelitian}}</v-card-text>
          </v-card>
          <v-card v-if="!hasAnggotaP">
            <v-card-text>Tidak Ada Anggota Peneliti</v-card-text>
          </v-card>
        </v-container>
      </material-card>
      <material-card
        color="blue"
        title="Identitas Usulan Penelitian - Anggota Peneliti Non-Poltekkes"
      >
        <v-container>
          <v-card v-show="hasAnggotaNP" v-for="(anggotaNP, index) in dataAnggotaNP" :key="index">
            <v-card-text>Anggota {{indext+1}}.</v-card-text>
            <v-card-text>{{anggotaP.nama}}</v-card-text>
            <v-card-text>{{anggotaP.namaInstitusi}} - {{anggotaP.peranPeneliti}}</v-card-text>
            <v-card-text>{{anggotaP.tugasPenelitian}}</v-card-text>
          </v-card>
          <v-card v-if="!hasAnggotaNP">
            <v-card-text>Tidak Ada Anggota Peneliti Non-Poltekkes</v-card-text>
          </v-card>
        </v-container>
      </material-card>
    </div>

    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import { apiServer } from "../../../setup-axios";
import { required } from "vuelidate/lib/validators";

export default {
  data: () => ({
    isLoading: true,
    dialog: false,
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    dataKetua: [],
    dataAnggotaP: [],
    hasAnggotaP: false,
    dataAnggotaNP: [],
    hasAnggotaNP: false,
    isDosen: true,
    value: 0,
    listJenisTkt: [],
    idJenisTktSelected: "",
    tktMaxLvl: 0,
    tktMinLvl: 0,
    activeLvl: 0,
    tktData: {
      definisi: "",
      indikatorTktList: []
    },
    headers: [
      { text: "Indikator", value: "deskripsi" },
      { text: "%", value: "percentage", sortable: "false", width: "10%" }
    ],
    averageLvlPoint: 0,
    isLastLevel: false,
    tktMessage: "",
    isFlowFinished: -1,
    tktSaatIni: "",
    tktTarget: "",
    percentageList: [
      { label: "0%", value: 0 },
      { label: "20%", value: 20 },
      { label: "40%", value: 40 },
      { label: "60%", value: 60 },
      { label: "80%", value: 80 },
      { label: "100%", value: 100 }
    ],
    listIlmuLv1: [],
    listIlmuLv2: [],
    listIlmuLv3: [],
    listTema: [],
    listTopik: [],
    listLamaKegiatan: [],
    editedItem: {
      judul: "",
      idIlmuLv1: "",
      idIlmuLv2: "",
      idIlmuLv3: "",
      idTema: "",
      idTopik: "",
      tahunMulaiPelaksanaan: "",
      tahunSelesaiPelaksanaan: "",
      lamaKegiatan: "",
      tktSaatIni: "",
      tktTarget: "",
      idKelompokTkt: "",
      idRumpunIlmu: ""
    },
    anggotaPenelitiView: false,
    editJudul: false,
    topikOrganisasi: {},
  }),

  validations: {
    idJenisTktSelected: {
      required
    },
    tktSaatIni: {
      required
    },
    tktTarget: {
      required
    },
    editedItem: {
      idIlmuLv1: {
        required
      },
      idIlmuLv2: {
        required
      },
      idIlmuLv3: {
        required
      },
      tahunMulaiPelaksanaan: {
        required
      },
      lamaKegiatan: {
        required
      },
      idTema: {
        required
      },
      idTopik: {
        required
      }
    }
  },

  components: {
    AnggotaPeneliti: () =>
      import("@/views/penelitian/overview/AnggotaPeneliti.vue")
  },

  computed: {
    jenisTktErrors() {
      const errors = [];
      if (!this.$v.idJenisTktSelected.$dirty) return errors;
      !this.$v.idJenisTktSelected.required &&
        errors.push("Jenis TKT harus di isi");
      return errors;
    },
    tktSaatIniErrors() {
      const errors = [];
      if (!this.$v.tktSaatIni.$dirty) return errors;
      !this.$v.tktSaatIni.required &&
        errors.push("*TKT saat ini harus di ukur");
      return errors;
    },
    tktTargetErrors() {
      const errors = [];
      if (!this.$v.tktTarget.$dirty) return errors;
      !this.$v.tktTarget.required && errors.push("*TKT target harus di ukur");
      return errors;
    },
    rumpunIlmuLv1Errors() {
      const errors = [];
      if (!this.$v.editedItem.idIlmuLv1.$dirty) return errors;
      !this.$v.editedItem.idIlmuLv1.required &&
        errors.push("Rumpun ilmu level 1 harus di isi");
      return errors;
    },
    rumpunIlmuLv2Errors() {
      const errors = [];
      if (!this.$v.editedItem.idIlmuLv2.$dirty) return errors;
      !this.$v.editedItem.idIlmuLv2.required &&
        errors.push("Rumpun ilmu level 2 harus di isi");
      return errors;
    },
    rumpunIlmuLv3Errors() {
      const errors = [];
      if (!this.$v.editedItem.idIlmuLv3.$dirty) return errors;
      !this.$v.editedItem.idIlmuLv3.required &&
        errors.push("Rumpun ilmu level 3 harus di isi");
      return errors;
    },
    temaErrors() {
      console.log("temaErrors ", this.$v.editedItem.idTema.$dirty);
      console.log("temaErrors ", this.$v.editedItem.idTema.required);

      const errors = [];
      if (!this.$v.editedItem.idTema.$dirty) return errors;
      !this.$v.editedItem.idTema.required && errors.push("Tema harus di isi");
      return errors;
    },
    topikErrors() {
      const errors = [];
      if (!this.$v.editedItem.idTopik.$dirty) return errors;
      !this.$v.editedItem.idTopik.required && errors.push("Topik harus di isi");
      return errors;
    },
    tahunMulaiPelaksanaanErrors() {
      const errors = [];
      if (!this.$v.editedItem.tahunMulaiPelaksanaan.$dirty) return errors;
      !this.$v.editedItem.tahunMulaiPelaksanaan.required &&
        errors.push("Tahun pelaksanaan harus di isi");
      return errors;
    },
    lamaKegiatanErrors() {
      const errors = [];
      if (!this.$v.editedItem.lamaKegiatan.$dirty) return errors;
      !this.$v.editedItem.lamaKegiatan.required &&
        errors.push("Lama kegiatan harus di isi");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.isDosen = this.$store.state.user.role.roleCode === "DOSEN";
    this.getData();
    this.getJenisTkt();
    this.getIlmuLevel1();
    this.getTema();
  },

  methods: {
    getIlmuLevel1() {
      apiServer
        .get("/rumpun-ilmu/0")
        .then(response => {
          this.listIlmuLv1 = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getIlmuLevel2() {
      apiServer
        .get("/rumpun-ilmu/" + this.editedItem.idIlmuLv1)
        .then(response => {
          this.listIlmuLv2 = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getIlmuLevel3() {
      apiServer
        .get("/rumpun-ilmu/" + this.editedItem.idIlmuLv2)
        .then(response => {
          this.listIlmuLv3 = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getTema() {
      apiServer
        .get("/tema")
        .then(response => {
          this.listTema = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getTopik() {
      apiServer
        .get("/topik/" + this.editedItem.idTema)
        .then(response => {
          this.listTopik = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getLamaKegiatan() {
      apiServer
        .get("/lama-kegiatan/" + this.$route.params.id)
        .then(response => {
          this.listLamaKegiatan = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    submit() {
      this.tktSaatIni = this.activeLvl;
      this.tktTarget = this.activeLvl + 1;
      this.close();
    },
    submitAll() {
      this.$v.$reset();
      this.mappingValue();
      this.$v.$touch();
      console.log("submitAll", this.$v);
      if (!this.$v.$invalid) {
        apiServer.get("/validasi/date/isAllowed").then(response => {
          var pesanValidasi = response.data;
          if (pesanValidasi !== "Allowed") {
            this.$store.dispatch("setStatus", {
              snackbarColor: "danger",
              snackbarText: pesanValidasi
            });
            this.$router.replace("/usulan");
          } else {
            this.putData();
          }
        });
      }
    },
    mappingValue() {
      this.editedItem.tktSaatIni = this.tktSaatIni;
      this.editedItem.tktTarget = this.tktTarget;
      this.editedItem.idKelompokTkt = this.idJenisTktSelected;
      this.editedItem.idRumpunIlmu = this.editedItem.idIlmuLv3;
      this.editedItem.tahunSelesaiPelaksanaan =
        +this.editedItem.tahunMulaiPelaksanaan +
        +this.editedItem.lamaKegiatan -
        1;
    },
    putData() {
      apiServer
        .put("/identitas-usulan/" + this.$route.params.id, this.editedItem)
        .then(response => {
          this.showSnackbar(
            "success",
            "Berhasil menambahkan identitas pengusul"
          );
          this.anggotaPenelitiView = true;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    averageLvlPointFormatted() {
      return this.averageLvlPoint.toFixed(0);
    },
    hitungTkt() {
      this.$v.$touch();
      console.log("debug: ", this.$v)
      if (!this.$v.idJenisTktSelected.$error) {
        this.getMinMaxTkt();
        this.getTktData();
        this.dialog = true;
      }

    },
    close() {
      this.$v.$reset();
      this.dialog = false;
      this.activeLvl = 0;
      this.averageLvlPoint = 0;
      this.tktMessage = "";
      this.isFlowFinished = -1;
    },
    getMinMaxTkt() {
      apiServer
        .get("/skema-penelitian/" + this.$route.params.id)
        .then(response => {
          this.tktMaxLvl = response.data.tktMax;
          this.tktMinLvl = response.data.tktMin;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    nextLevel() {
      this.averageLvlPoint = 0;
      this.tktMessage = "";
      this.isFlowFinished = -1;
      this.getTktData();
    },
    getTktData() {
      this.isLoading = true;
      this.activeLvl++;
      if (this.activeLvl == this.tktMaxLvl - 1) {
        this.isLastLevel = true;
      } else {
        this.isLastLevel = false;
      }
      apiServer
        .get(
          "/pengukuran-tkt/" + this.idJenisTktSelected + "/" + this.activeLvl
        )
        .then(response => {
          this.isLoading = false;
          this.tktData = response.data;
          console.log("tkt data : ", this.tktData);
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getData() {
      console.log("get data..");
      apiServer
        .get("/identitas-usulan/" + this.$route.params.id)
        .then(response => {
          this.getLamaKegiatan();
          this.editedItem = response.data;
          this.idJenisTktSelected = response.data.idKelompokTkt;
          this.tktSaatIni = response.data.tktSaatIni;
          this.tktTarget = response.data.tktTarget;
          this.editedItem.lamaKegiatan = response.data.lamaKegiatan;
          if (this.editedItem.idTema != null) this.getTopik();
          if (this.editedItem.idIlmuLv1 != null) this.getIlmuLevel2();
          if (this.editedItem.idIlmuLv2 != null) this.getIlmuLevel3();
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      apiServer
        .get("/timPeneliti/getDataKetua/" + this.$route.params.id)
        .then(response => {
          this.dataKetua = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      apiServer
        .get("/timPeneliti/getDataAnggota/" + this.$route.params.id)
        .then(response => {
          this.dataAnggotaP = response.data;
          if (this.dataAnggotaP != []) {
            this.hasAnggotaP = true;
          }
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      apiServer
        .get("/timPeneliti/getDataAnggotaNP/" + this.$route.params.id)
        .then(response => {
          this.dataAnggotaNP = response.data;
          if (this.dataAnggotaNP != []) {
            this.hasAnggotaNP = true;
          }
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
      apiServer
        .get('/topik-unggulan/' + this.$store.state.user.userId)
        .then(response => {
          this.topikOrganisasi = response.data
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
    getJenisTkt() {
      apiServer
        .get("/jenis-tkt")
        .then(response => {
          this.listJenisTkt = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },
    averageScoreProcess() {
      var sumPercentage = 0;
      console.log("indikator jml ", this.tktData.indikatorTktList.length);
      for (var i = 0; i < this.tktData.indikatorTktList.length; i++) {
        sumPercentage =
          +sumPercentage + +this.tktData.indikatorTktList[i].percentage;
      }
      this.averageLvlPoint =
        sumPercentage / this.tktData.indikatorTktList.length;
      if (this.averageLvlPointFormatted() >= 80 && !this.isLastLevel) {
        this.tktMessage =
          "Nilai indikator anda " +
          this.averageLvlPointFormatted() +
          ", silakan klik tombol lanjutkan untuk pengisian tkt di level selanjutnya";
        this.isFlowFinished = 0;
      } else if (
        this.averageLvlPointFormatted() < 80 &&
        this.activeLvl < this.tktMinLvl
      ) {
        this.tktMessage =
          "Level TKT saat ini belum memenuhi syarat minimal level di skema ini" +
          ", level minimal yang harus dicapai adalah " +
          this.tktMinLvl;
        this.isFlowFinished = -1;
      } else {
        this.tktMessage =
          "Nilai indikator anda " +
          this.averageLvlPointFormatted() +
          ", level yang dicapai adalah " +
          this.activeLvl +
          ". silakan klik tombol simpan";
        this.isFlowFinished = 1;
      }
    },
    ubahJudul() {
      this.editJudul = !this.editJudul
      if (!this.editJudul) {
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            var penelitian = response.data
            penelitian.judul = this.editedItem.judul
            apiServer
              .put('/penelitian', penelitian)
              .then(response => {
                this.showSnackbar('success', 'Judul berhasil disimpan')
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      }
    },
  },
}
</script>

<style lang="scss" scoped>
// .search{
//   margin-left: 20%;
// }
#tblPokok td {
  border: 1px dotted black;
}
</style>
