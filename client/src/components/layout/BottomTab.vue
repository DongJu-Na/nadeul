<template>
  <nav
    class="fixed bottom-0 left-0 right-0 mx-auto flex justify-around bg-white border-t border-zinc-100 drop-shadow-top z-[100]"
    :style="`width:var(--app-width)`"
  >
    <router-link
      :to="menu.link"
      v-for="menu in menuItems"
      :key="menu.id"
      class="flex flex-col items-center py-[15px] flex-1"
    >
      <IconHome
        v-if="menu.icon === 'home'"
        :size="20"
        :color="route.fullPath === menu.link ? '#059669' : '#D9D9D9'"
      />
      <IconTrip
        v-if="menu.icon === 'trip'"
        :size="20"
        :color="route.fullPath.includes(menu.link) ? '#059669' : '#D9D9D9'"
      />
      <IconStamp
        v-if="menu.icon === 'stamp'"
        :size="20"
        :color="route.fullPath === menu.link ? '#059669' : '#D9D9D9'"
      />
      <IconReview
        v-if="menu.icon === 'review'"
        :size="20"
        :color="route.fullPath === menu.link ? '#059669' : '#D9D9D9'"
      />
      <IconHeart
        v-if="menu.icon === 'heart'"
        :size="20"
        :color="route.fullPath === menu.link ? '#059669' : '#D9D9D9'"
      />
      <IconBasket
        v-if="menu.icon === 'basket'"
        :size="20"
        :color="route.fullPath.includes(menu.link) ? '#059669' : '#D9D9D9'"
      />
      <span v-if="menu.icon === 'home'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn1') }}</span>
      <span v-if="menu.icon === 'trip'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn2') }}</span>
      <span v-if="menu.icon === 'stamp'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn3') }}</span>
      <span v-if="menu.icon === 'review'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn4') }}</span>
      <span v-if="menu.icon === 'heart'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn5') }}</span>
      <span v-if="menu.icon === 'basket'" class="text-xs text-zinc-400 mt-[1px]">{{ $t('bottomBtn6') }}</span>
    </router-link>
  </nav>
</template>

<script setup>
import { ref, onMounted, onUpdated, watch } from 'vue';
import { useRoute } from 'vue-router';
import IconHome from '@/components/common/icons/IconHome.vue';
import IconTrip from '@/components/common/icons/IconTrip.vue';
import IconStamp from '@/components/common/icons/IconStamp.vue';
import IconReview from '@/components/common/icons/IconReview.vue';
import IconHeart from '@/components/common/icons/IconHeart.vue';
import IconBasket from '@/components/common/icons/IconBasket.vue';

const menuItems = ref([
  {
    id: 1,
    name: '홈',
    icon: 'home',
    link: '/home',
  },
  {
    id: 2,
    name: '여행',
    icon: 'trip',
    link: '/trips',
  },
  {
    id: 3,
    name: '스탬프',
    icon: 'stamp',
    link: '/stamp',
  },
  {
    id: 4,
    name: '리뷰',
    icon: 'review',
    link: '/review',
  },
  {
    id: 5,
    name: '찜',
    icon: 'heart',
    link: '/wish',
  },
  {
    id: 6,
    name: '마켓',
    icon: 'basket',
    link: '/market',
  },
]);

const route = useRoute();

watch(route, () => {
  // console.log(route.fullPath);
});
</script>

<style lang="scss" scoped>
.router-link-active {
}
</style>
