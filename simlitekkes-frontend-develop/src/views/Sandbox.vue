<template>
  <v-card class="mx-auto" width="300">
    <v-list-item v-for="(menu, i) in menus" :key="i">
      <v-list style="width:100%">
        <v-list-group value="true">
          <template v-slot:activator>
            <v-list-item-title v-text="menu[i,0]" />
          </template>
          <v-list-item v-for="(sub, j) in menu[1]" :key="j" link>
            <v-list-item-icon>
              <v-icon v-text="sub.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-title v-text="sub.label"></v-list-item-title>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-list-item>

    <hr />

    <v-list-item>
      <v-list>
        <v-list-group value="true">
          <template v-slot:activator>
            <v-list-item-title>Box</v-list-item-title>
          </template>
          <v-list-item v-for="(admin, i) in admins" :key="i" link>
            <v-list-item-icon>
              <v-icon v-text="admin[1]"></v-icon>
            </v-list-item-icon>
            <v-list-item-title v-text="admin[0]"></v-list-item-title>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-list-item> 
  </v-card>
</template>

<script>
export default {
  data: () => ({
    admins: [
      ["Management", "mdi-alarm"],
      ["Settings", "mdi-settings"]
    ],
    cruds: [
      ["Create", "add"],
      ["Read", "insert_drive_file"],
      ["Update", "update"],
      ["Delete", "delete"]
    ],
    menus: [
      [
        "Box",
        [
          {
            label: 'Management',
            icon: 'mdi-alarm'
          },
          {
            label: 'Setting',
            icon: 'mdi-settings'
          }
        ]
      ],
      [
        "Management",
        [
          {
            label: 'Management',
            icon: 'mdi-alarm'
          },
          {
            label: 'Settingz',
            icon: 'mdi-settings'
          }
        ]
      ]
    ]
  })
};
</script>