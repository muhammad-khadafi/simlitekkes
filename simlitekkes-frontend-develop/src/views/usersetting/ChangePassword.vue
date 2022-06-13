<template>
  <v-container fill-height fluid>
    <v-row justify="center">
      <v-col cols="12" md="8">
        <material-card color="blue" title="Ubah Password" text="Lengkapi Ubah Password Anda">
          <v-form ref="form" v-model="isFormValid">
            <v-container class="py-0">
              <v-row>
                <v-col cols="6">
                        <v-text-field
                            class="mt-5 ml-12 mr-4"
                            ref="password"
                            v-model="defaultItem.passwordOld"
                            :rules="[() => !!defaultItem.passwordOld || 'Kolom harus terisi']"
                            :append-icon="showPassword2 ? 'mdi-eye-off' : 'mdi-eye'"
                            :type="showPassword2 ? 'text' : 'password'"
                            prepend-inner-icon="mdi-lock"
                            label="Password Lama"
                            placeholder="Password Lama"
                            counter
                            required
                            outlined
                            @click:append="showPassword2 = !showPassword2" 
                        />
                </v-col>
                <v-col cols="6"></v-col>
                <v-col cols="6">
                  <v-text-field
                    class="mt-5 ml-12 mr-4"
                    ref="password"
                    v-model="defaultItem.password"
                    :rules="[() => !!defaultItem.password || 'Kolom harus terisi',
                             () => /[a-z]/.test(defaultItem.password) && /[A-Z]/.test(defaultItem.password) && /[0-9]/.test(defaultItem.password)
                             || 'Password harus mengandung huruf besar, huruf kecil dan angka',
                             () => defaultItem.password.length >= 8 || 'Panjang Minimal 8 karakter']"
                    :append-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
                    :type="showPassword ? 'text' : 'password'"
                    prepend-inner-icon="mdi-lock"
                    label="Password Baru"
                    placeholder="Password Baru"
                    counter
                    required
                    outlined
                    @click:append="showPassword = !showPassword"
                  />
                </v-col>

                <v-col cols="6">
                  <v-text-field
                    class="mt-5 mr-12 ml-4"
                    ref="password"
                    v-model="defaultItem.passwordNew"
                    :rules="[() => !!defaultItem.passwordNew || 'Kolom harus terisi',
                            () => (defaultItem.password === defaultItem.passwordNew) || 'Password harus sama dengan Password Baru']"
                    :append-icon="showPassword1 ? 'mdi-eye-off' : 'mdi-eye'"
                    :type="showPassword1 ? 'text' : 'password'"
                    prepend-inner-icon="mdi-lock"
                    label="Konfirmasi Ulang Password Baru"
                    placeholder="Konfirmasi Ulang Password Baru"
                    counter
                    required
                    outlined
                    @click:append="showPassword1 = !showPassword1"
                  />
                </v-col>

                <!-- {{ defaultItem }} -->

                <v-col cols="12" class="text-right">
                  <v-btn
                    :disabled="!isFormValid"
                    color="blue"
                    @click="save"
                    class="white--text"
                  >Simpan</v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>
      <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="3500" :top="true">
        {{ snackbarText }}
        <v-btn dark text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>

<script>
import moment from "moment";
import { apiServer } from "../../setup-axios";
import { sameAs, required } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    isFormValid: false,
    menu: false,
    dialog: false,
    showPassword: false,
    showPassword1: false,
    showPassword2: false,
    defaultItem: {
      id: "",
      password: "",
      passwordNew: "",
      passwordOld: ""
    }
  }),

  created() {
    const userString = JSON.parse(localStorage.getItem("user"));
    this.defaultItem.id = userString.userId;
    console.log("cekidot ", userString);
  },

  methods: {
    save() {
      apiServer
        .put("/userweb/change-password", this.defaultItem)
        .then(response => {
          this.showSnackbar("success", "Password anda telah diubah");
          this.$refs.form.reset();
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },
    reset() {
      this.defaultItem.password = " ";
      this.defaultItem.passwordNew = " ";
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    }
  }
};
</script>
