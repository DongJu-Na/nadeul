<template>
  <div class="flex my-3">
    <Swiper
      :slides-per-view="1.6"
      :space-between="20"
      :slides-offset-before="20"
      :slides-offset-after="20"
      :loop="true"
      v-show="isLoaded"
    >
      <SwiperSlide v-for="photoData in photoList" :key="photoData.galContentId">
        <router-link :to="{name: 'trip list', query: {keyword: photoData.galTitle , searchType: 'keyword' }}">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute bottom-0 z-10 p-5 text-white drop-shadow-sm">
              <dt class="text-sm">{{ photoData.galPhotographyLocation }}</dt>
              <dd class="text-2xl font-semibold leading-7 break-keep">{{ photoData.galTitle }}</dd>
            </dl>
            <div
              class="absolute bottom-0 w-full h-1/2 rounded-2xl bg-gradient-to-t from-black/60"
            ></div>
            <img :src="photoData.galWebImageUrl" class="h-[350px] w-[367.5px] object-cover" />
          </div>
        </router-link>
      </SwiperSlide>

      <!--<SwiperSlide>
        <router-link to="/trips/1">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/photo-1532274402911-5a369e4c4bb5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide>
      <SwiperSlide>
        <router-link to="/trips/2">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/34/BA1yLjNnQCI1yisIZGEi_2013-07-16_1922_IMG_9873.jpg?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide>
      <SwiperSlide>
        <router-link to="/trips/3">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/photo-1559827291-72ee739d0d9a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide> -->
    </Swiper>
    <LoadingDot v-if="!isLoaded" />
  </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getGalleryList } from '@/api';
import LoadingDot from '@/components/common/templates/LoadingDot.vue';

const router = useRouter();
const photoList = ref(null);
const isLoaded = ref(false);

function getRandomInteger() {
  const min = 1;
  const max = 520;
  return Math.floor(Math.random() * (max - min + 1) + min);
}

const fetchPhotoList = async () => {
  try {
    const randomVal = getRandomInteger();
    const payload = {
      numOfRows: 10,
      pageNo: randomVal,
      arrange: 'A',
      _type: 'json',
    };
    const {
      status,
      data: {
        response: {
          body: {
            items: { item },
          },
        },
      },
    } = await getGalleryList(payload);
    console.log(item);
    if (status === 200 && item) {
      console.log('test: ', item);
      photoList.value = item;
      console.log(photoList);
      isLoaded.value = true;
    }
  } catch (error) {
    console.log(error);
  }
};

onMounted(async () => {
  await router.isReady();
  fetchPhotoList();
});
</script>
