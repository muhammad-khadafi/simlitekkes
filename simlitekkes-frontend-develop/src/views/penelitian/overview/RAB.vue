<template>
  <v-container fluid>
    <v-card class="mx-auto mb-4">
      <v-card-text v-show="isNotif" class="error font-weight-bold">{{ notifMessage }}</v-card-text>
    </v-card>
    <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
      <v-expansion-panel v-for="(rab, index) in rabList" :key="index">
        <v-expansion-panel-header>
          Tahun {{ rab.tahun }}
          <div class="flex-grow-1" />
          Total Biaya : {{ rab.totalBiaya }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-data-table
            :headers="headers"
            :items="rab.rencanaAnggaranListPerYear"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
          >
            <template v-slot:item.action="{ item }">
              <v-btn
                class="pl-0 ml-0"
                color="blue darken-1"
                text
                :disabled="!isEditable"
                @click="editItem(item)"
              >EDIT</v-btn>
              <v-btn
                class="pl-0 pr-0"
                color="blue darken-1"
                text
                :disabled="!isEditable"
                @click="deleteItem(item)"
              >HAPUS</v-btn>
            </template>
          </v-data-table>

          <v-btn
            color="blue"
            dark
            class="mt-2"
            :disabled="!isEditable"
            @click="showDialog(rab)"
          >Tambah</v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Rencana Anggaran Belanja</span>
        </v-card-title>

        <v-card-text>
          <v-container>
            <h2 class="center">Tahun {{ editedItem.tahun }}</h2>
            <h3 class="center">Mengacu ke SBM Tahun Berjalan</h3>
            <v-row>
              <v-col cols="12">
                <v-select
                  v-model="editedItem.idKomponenRab"
                  :items="jenisPembelanjaan"
                  item-text="nama"
                  item-value="id"
                  label="Jenis Pembelanjaan *"
                  required
                  :error-messages="jenisPembelanjaanErrors"
                  @input="$v.editedItem.idKomponenRab.$touch()"
                  @blur="$v.editedItem.idKomponenRab.$touch()"
                />
              </v-col>

              <v-col cols="12">
                <v-text-field
                  v-model="editedItem.item"
                  label="Item *"
                  required
                  :error-messages="itemErrors"
                  @input="$v.editedItem.item.$touch()"
                  @blur="$v.editedItem.item.$touch()"
                />
              </v-col>
              <v-col cols="4">
                <v-text-field
                  v-model="editedItem.volume"
                  label="Volume *"
                  required
                  :error-messages="volumeErrors"
                  @change="calculateBiaya()"
                  @input="$v.editedItem.volume.$touch()"
                  @blur="$v.editedItem.volume.$touch()"
                />
              </v-col>
              <v-col cols="4">
                <v-text-field
                  v-model="editedItem.satuan"
                  label="Satuan *"
                  required
                  counter
                  :error-messages="satuanErrors"
                  @input="$v.editedItem.satuan.$touch()"
                  @blur="$v.editedItem.satuan.$touch()"
                />
              </v-col>
              <v-col cols="4">
                <v-text-field
                  v-model="editedItem.biayaSatuan"
                  label="Biaya Satuan *"
                  prefix="Rp"
                  required
                  :error-messages="biayaSatuanErrors"
                  @change="calculateBiaya()"
                  @input="$v.editedItem.biayaSatuan.$touch()"
                  @blur="$v.editedItem.biayaSatuan.$touch()"
                />
              </v-col>

              <v-col cols="4">
                <v-text-field v-model="biayaTotal" label="Biaya Total" :disabled="true" />
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <div class="flex-grow-1" />
          <v-btn color="blue darken-1" text @click="close">Batal</v-btn>
          <v-btn color="blue darken-1" text @click="save">Kirim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
      {{ snackbarText }}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import { apiServer } from "../../../setup-axios";
import { required, numeric, maxLength } from "vuelidate/lib/validators";

export default {
  data: () => ({
    panel: [],
    disabled: false,
    readonly: false,
    dialog: false,
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    rabList: [],
    jenisPembelanjaan: [],
    isLoading: true,
    cost: 1700000,
    headers: [
      { text: "Komponen RAB", value: "namaKomponen", sortable: false },
      { text: "Item", value: "item", sortable: false },
      { text: "Satuan", value: "satuan", sortable: false },
      { text: "Volume", value: "volume", sortable: false },
      { text: "Biaya Satuan", value: "biayaSatuanFormatted", sortable: false },
      { text: "Total", value: "subTotalFormatted", sortable: false },
      { text: "Actions", value: "action", sortable: false }
    ],
    dataTahun: [],
    editedIndex: -1,
    editedItem: {
      idPenelitian: "",
      idKomponenRab: "",
      tahun: "",
      item: "",
      volume: "",
      satuan: "",
      biayaSatuan: ""
    },
    defaultItem: {
      idPenelitian: "",
      idKomponenRab: "",
      tahun: "",
      item: "",
      volume: "",
      satuan: "",
      biayaSatuan: ""
    },
    biayaTotal: "",
    isEditable: false,
    isNotif: false,
    notifMessage: "",
    totalRabAllYears: 0,
    minBudget: 0,
    maxBudget: 0
  }),

  validations: {
    editedItem: {
      idKomponenRab: {
        required
      },
      item: {
        required
      },
      volume: {
        required,
        numeric
      },
      satuan: {
        required,
        maxLength: maxLength(20)
      },
      biayaSatuan: {
        required,
        numeric
      }
    }
  },

  computed: {
    jenisPembelanjaanErrors() {
      const errors = [];
      if (!this.$v.editedItem.idKomponenRab.$dirty) return errors;
      !this.$v.editedItem.idKomponenRab.required &&
        errors.push("Harus di isi!");
      return errors;
    },
    itemErrors() {
      const errors = [];
      if (!this.$v.editedItem.item.$dirty) return errors;
      !this.$v.editedItem.item.required && errors.push("Harus di isi!");
      return errors;
    },
    volumeErrors() {
      const errors = [];
      if (!this.$v.editedItem.volume.$dirty) return errors;
      !this.$v.editedItem.volume.required && errors.push("Harus di isi!");
      !this.$v.editedItem.volume.numeric && errors.push("Harus berisi angka");
      return errors;
    },
    satuanErrors() {
      const errors = [];
      if (!this.$v.editedItem.satuan.$dirty) return errors;
      !this.$v.editedItem.satuan.required && errors.push("Harus di isi!");
      !this.$v.editedItem.satuan.maxLength &&
        errors.push("Satuan maksimal 20 karakter");
      return errors;
    },
    biayaSatuanErrors() {
      const errors = [];
      if (!this.$v.editedItem.biayaSatuan.$dirty) return errors;
      !this.$v.editedItem.biayaSatuan.required && errors.push("Harus di isi!");
      !this.$v.editedItem.biayaSatuan.numeric &&
        errors.push("Harus berisi angka");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.getData();
    this.getJenisPembelanjaan();
    if (this.$store.state.user.role.roleCode === "DOSEN") {
      this.isEditable = true;
    }
    this.getBudgetInfo();
  },

  methods: {
    getBudgetInfo() {
      apiServer
        .get("/dana-anggaran/" + this.$route.params.id)
        .then(response => {
          console.log("dana-anggaran ", response.data);
          this.minBudget = response.data.jumlahDanaMin;
          this.maxBudget = response.data.jumlahDanaMax;

          console.log("dana-anggaran ", this.minBudget);
        })
        .catch(error => {
          console.log("There was an error:", error);
        });
    },
    getData() {
      this.isLoading = true;
      apiServer
        .get("/rencana-anggaran/" + this.$route.params.id)
        .then(response => {
          this.rabList = response.data;
          console.log("rab list : ", response.data);
          this.totalRabAllYears = 0;
          response.data.forEach(this.accumulateAllYears);
          this.validationRabAllYears();
          this.openingPanel();
        })
        .catch(error => {
          console.log("There was an error:", error); // Logs out the error
        });
      this.isLoading = false;
    },
    accumulateAllYears(data) {
      this.totalRabAllYears = this.totalRabAllYears + data.totalBiayaNum;
    },
    openingPanel() {
      let i;
      for (i = 0; i < this.rabList.length; i++) {
        this.panel.push(i);
      }
    },
    validationRabAllYears() {
      console.log(
        "Total rab all years : " +
          new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
          }).format(this.totalRabAllYears)
      );
      console.log(
        "Dana Minimum : " +
          new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
          }).format(this.minBudget)
      );
      console.log(
        "Dana maksimum : " +
          new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
          }).format(this.maxBudget)
      );
      if (this.totalRabAllYears < this.minBudget) {
        this.isNotif = true;
        this.notifMessage =
          "Jumlah rab semua tahun kurang dari dana minimum, dana minimum : " +
          new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
          }).format(this.minBudget);
      } else if (this.totalRabAllYears > this.maxBudget) {
        this.isNotif = true;
        this.notifMessage =
          "Jumlah rab semua tahun lebih dari dana maksimum, dana maksimum : " +
          new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
          }).format(this.maxBudget);
      } else {
        this.isNotif = false;
      }
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },
    getJenisPembelanjaan() {
      apiServer
        .get("/komponen-rab")
        .then(response => {
          console.log("result getjenispembelanjaan:", response.data);
          this.jenisPembelanjaan = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error); // Logs out the error
        });
    },
    postData() {
      apiServer
        .post("/rencana-anggaran", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Add new data successful");
        })
        .catch(error => {
          console.log("There was an error:", error);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    updateData() {
      apiServer
        .put("/rencana-anggaran", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Edit data successful");
        })
        .catch(error => {
          console.log("There was an error:", error);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    close() {
      this.$v.$reset();
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
        this.biayaTotal = "";
      }, 300);
    },
    calculateBiaya() {
      if (this.editedItem.volume != "" && this.editedItem.biayaSatuan != "") {
        this.biayaTotal = new Intl.NumberFormat("id-ID", {
          style: "currency",
          currency: "IDR"
        }).format(this.editedItem.volume * this.editedItem.biayaSatuan);
      }
    },
    save() {
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.editedItem.idPenelitian = this.$route.params.id;
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
        this.close();
      }
    },
    showDialog(rab) {
      this.dialog = true;
      this.editedItem.tahun = rab.tahun;
    },
    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    editItem(item) {
      this.editedIndex = 1;
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
      this.calculateBiaya();
    },
    deleteData(id) {
      apiServer
        .delete("/rencana-anggaran/" + id)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Delete data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    }
  }
};
</script>

<style lang="scss" scoped>
#tblPokok td {
  border: 1px dotted black;
}
</style>
