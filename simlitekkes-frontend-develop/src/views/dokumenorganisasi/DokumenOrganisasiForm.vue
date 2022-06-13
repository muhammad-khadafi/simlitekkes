<template>
  <v-container fill-height fluid>
    <v-row justify="center">
      <v-col cols="10">
        <material-card color="blue" v-bind:title="formTitle">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                v-model="editedItem.judul"
                label="Judul"
                @input="$v.editedItem.judul.$touch()"
                @blur="$v.editedItem.judul.$touch()"
                :error-messages="judulErrors"
              ></v-text-field>
            </v-col>
            <v-col cols="10">
              <v-select
                v-model="editedItem.idJenisDokumen"
                :items="jenisDokumen"
                item-text="nama"
                item-value="id"
                label="Nama Jenis Dokumen"
                required
                @input="$v.editedItem.idJenisDokumen.$touch()"
                @blur="$v.editedItem.idJenisDokumen.$touch()"
                :error-messages="jenisDokumenErrors"
              />
            </v-col>
            <v-col cols="10">
              <v-file-input
                v-model="fileUpload"
                @change="onFilePicked"
                label="Upload File"
                accept="application/pdf"
                ref="fileInput"
                :rules="rules"
                required
                @input="$v.fileUpload.$touch()"
                @blur="$v.fileUpload.$touch()"
                :error-messages="fileUploadErrors"
              />
            </v-col>
            <v-col cols="5">
              <v-text-field
                v-model="editedItem.nomorDokumen"
                label="Nomor Dokumen"
                @input="$v.editedItem.nomorDokumen.$touch()"
                @blur="$v.editedItem.nomorDokumen.$touch()"
                :error-messages="nomorDokumenErrors"
              ></v-text-field>
            </v-col>
            <v-col cols="5">
              <v-text-field
                v-model="editedItem.tahunTerbit"
                label="Tahun Terbit"
                type="number"
                @input="$v.editedItem.tahunTerbit.$touch()"
                @blur="$v.editedItem.tahunTerbit.$touch()"
                :error-messages="tahunTerbitErrors"
              ></v-text-field>
            </v-col>
            <v-col cols="10" justify="right">
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="blue darken-1" text @click="back">Batal</v-btn>
                <v-btn color="blue darken-1" text @click="save">Simpan</v-btn>
              </v-card-actions>
            </v-col>
          </v-row>
        </material-card>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import MaterialCard from "../../components/material/Card";
import { apiServer } from "../../setup-axios";
import { required, maxLength } from "vuelidate/lib/validators";

import CommonService from '../../services/CommonService.js';


export default {
  components: { MaterialCard },
  data: () => ({
    editMode: false,
    formTitle: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    editedItem: {
      id: "",
      idJenisDokumen: "",
      namaFile: "",
      filePath: "",
      nomorDokumen: "",
      tahunTerbit: "",
      username: "",
      idOrganisasi: "",
      judul: ""
    },
    jenisDokumen: [],
    fileUpload: null,
    errors: false,
    rules: [
      value =>
        !value ||
        value.size < 5000000 ||
        "File harus bertipe pdf dengan ukuran kurang dari 5MB"
    ],
    oldFileUpload: null
  }),

  validations: {
    editedItem: {
      idJenisDokumen: { required },
      judul: {required},
      nomorDokumen: { maxLength: maxLength(100) },
      tahunTerbit: { maxLength: maxLength(4) }
    },
    fileUpload: { required }
  },

  computed: {
    judulErrors() {
      const errors = [];
      if (!this.$v.editedItem.judul.$dirty) return errors;
      !this.$v.editedItem.judul.required &&
        errors.push("Judul harus di isi");
      return errors;
    },
    jenisDokumenErrors() {
      const errors = [];
      if (!this.$v.editedItem.idJenisDokumen.$dirty) return errors;
      !this.$v.editedItem.idJenisDokumen.required &&
        errors.push("Jenis dokumen harus di isi");
      return errors;
    },
    nomorDokumenErrors() {
      const errors = [];
      if (!this.$v.editedItem.nomorDokumen.$dirty) return errors;
      !this.$v.editedItem.nomorDokumen.maxLength &&
        errors.push("maksimal 100 character");
      return errors;
    },
    tahunTerbitErrors() {
      const errors = [];
      if (!this.$v.editedItem.tahunTerbit.$dirty) return errors;
      !this.$v.editedItem.tahunTerbit.maxLength &&
        errors.push("maksimal 4 character");
      return errors;
    },
    fileUploadErrors() {
      const errors = [];
      if (!this.$v.fileUpload.$dirty) return errors;
      !this.$v.fileUpload.required && errors.push("File harus di isi");
      return errors;
    }
  },

  created() {
    this.getJenisDokumen();
    if (this.$route.path === "/dokumen-organisasi-update") {
      this.editMode = true;
    }
    this.populateData();
  },

  methods: {
    populateData() {
      if (this.editMode) {
        this.formTitle = "Edit Dokumen Institusi";
        this.editedItem.id = this.$store.state.dokumenOrganisasi.id;
        this.editedItem.tahunTerbit = this.$store.state.dokumenOrganisasi.tahunTerbit;
        this.editedItem.nomorDokumen = this.$store.state.dokumenOrganisasi.nomorDokumen;
        this.editedItem.idJenisDokumen = this.$store.state.dokumenOrganisasi.idJenisDokumen;
        this.editedItem.namaFile = this.$store.state.dokumenOrganisasi.namaFile;
        this.editedItem.judul = this.$store.state.dokumenOrganisasi.judul;
        this.getFile();
      } else {
        this.formTitle = "Tambah Dokumen Institusi";
      }
    },
    getFile() {
      console.log("debug file name : " + this.editedItem.namaFile);
      apiServer
        .get("/dokumen-organisasi-file/" + this.editedItem.id)
        .then(response => {
          this.fileUpload = CommonService.dataURLtoFile(
            response.data,
            this.editedItem.namaFile
          );
          this.oldFileUpload = this.fileUpload;
          const fileReader = new FileReader();
          fileReader.readAsDataURL(this.fileUpload);
        });
    },
    // dataURLtoFile(dataurl, filename) {
    //   console.log("DATA URL " + dataurl);
    //   var arr = dataurl.split(","),
    //     mime = arr[0].match(/:(.*?);/)[1],
    //     bstr = atob(arr[1]),
    //     n = bstr.length,
    //     u8arr = new Uint8Array(n);

    //   while (n--) {
    //     u8arr[n] = bstr.charCodeAt(n);
    //   }

    //   return new File([u8arr], filename, { type: mime });
    // },
    resetData() {
      this.editedItem.judul = "";
      this.editedItem.idJenisDokumen = "";
      this.editedItem.nomorDokumen = "";
      this.editedItem.tahunTerbit = "";
      this.fileUpload = null;
    },
    showSnackbar(color, message) {
      this.snackbar = true;
      this.snackbarColor = color;
      this.snackbarText = message;
    },
    getJenisDokumen() {
      apiServer
        .get("/jenis-dokumen-organisasi/")
        .then(response => {
          this.jenisDokumen = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    back() {
      this.$router.replace("/dokumen-organisasi");
    },
    save() {
      this.$v.$touch();
      if (!this.$v.$invalid) {
        if (this.editMode) {
          this.updateData();
        } else {
          this.addNewData();
        }
      }
    },

    updateData() {
      if(this.oldFileUpload == this.fileUpload){
        apiServer
            .put("/dokumen-organisasi/", this.editedItem)
            .then(response => {
              this.$store.dispatch("setDokumenOrganisasi", {
                snackbarColor : "success",
                message: "berhasil mengubah dokumen organisasi"
              });
              this.resetData();
              this.back();
            })
            .catch(error => {
              console.log("There was an error:", error.response);
            });
      } else {
        const formData = new FormData();
        formData.append("file", this.fileUpload);
        apiServer
        .put("/dokumen-organisasi-upload/", formData)
        .then(response => {
          this.editedItem.namaFile = response.data.fileName;
          this.editedItem.filePath = response.data.filePath;
          this.editedItem.idOrganisasi = this.$store.state.user.organizationId;
          this.editedItem.username = this.$store.state.user.username;
          apiServer
            .put("/dokumen-organisasi/", this.editedItem)
            .then(response => {
              this.$store.dispatch("setDokumenOrganisasi", {
                snackbarColor : "success",
                message: "berhasil mengubah dokumen organisasi"
              });
              this.resetData();
              this.back();
            })
            .catch(error => {
              console.log("There was an error:", error.response);
            });
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
      }
    },
    onFilePicked(event) {
      console.log("File at picked " + this.fileUpload);
    },
    addNewData() {
      const formData = new FormData();
      formData.append("file", this.fileUpload);
      apiServer
        .post("/dokumen-organisasi-upload/", formData)
        .then(response => {
          this.editedItem.namaFile = response.data.fileName;
          this.editedItem.filePath = response.data.filePath;
          this.editedItem.idOrganisasi = this.$store.state.user.organizationId;
          this.editedItem.username = this.$store.state.user.username;
          apiServer
            .post("/dokumen-organisasi/", this.editedItem)
            .then(response => {
              this.$store.dispatch("setDokumenOrganisasi", {
                snackbarColor : "success",
                message: "berhasil menambah dokumen organisasi"
              });
              this.resetData();
              this.back();
            })
            .catch(error => {
              console.log("There was an error:", error.response);
            });
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    }
  }
};
</script>

<style>
</style>