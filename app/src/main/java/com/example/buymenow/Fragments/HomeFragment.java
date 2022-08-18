package com.example.buymenow.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.buymenow.Adapters.CategoryAdapter;
import com.example.buymenow.Adapters.PopularityAdapter;
import com.example.buymenow.Activities.Detailed;
import com.example.buymenow.Models.CategoryModel;
import com.example.buymenow.Models.PopularModel;
import com.example.buymenow.R;
import com.example.buymenow.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RecyclerViewInterface {

    private List<CategoryModel> list;
    LinearLayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;
    private List<PopularModel> models;
    ImageView img1 , img2 , img3;
    CardView card0 , card1 , card2;
    TextView name , name2 , name3 , price , price2 , price3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/banner1.jpg?alt=media&token=a1093f48-7bcc-4ec5-afa6-4f4bd02a13ea" , ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/banner2.jpg?alt=media&token=9183b31f-4dac-4da1-940f-b2b10c07a24a" , ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/banner3.jpg?alt=media&token=923cd76b-c899-41a2-99fb-52a51ffade37" , ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/banner4.png?alt=media&token=82db82a8-5f2a-4fa3-ae3a-f9d29c27a2cf" , ScaleTypes.FIT));

        imageSlider.setImageList(slideModels , ScaleTypes.FIT);

        initData(); // Sets Recycler View Data.
        initRecyclerView(); // Gets Recycler View Data.

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);

        name = view.findViewById(R.id.name);
        name2 = view.findViewById(R.id.name2);
        name3 = view.findViewById(R.id.name3);
        price = view.findViewById(R.id.price);
        price2 = view.findViewById(R.id.price2);
        price3 = view.findViewById(R.id.price3);

        card0 = view.findViewById(R.id.card0);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);

        card0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity() , Detailed.class)
                        .putExtra("na" , name.getText().toString())
                        .putExtra("pri" , price.getText().toString())
                        .putExtra("img" , "https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/oppoReno9Pro5G.png?alt=media&token=13f5f365-3a64-45cc-8e79-5ec4e8be0dce")
                        .putExtra("icon1" , R.drawable.ram )
                        .putExtra("text1" , "12 GB RAM | 256 GB ROM")
                        .putExtra("icon2" , R.drawable.cpu)
                        .putExtra("text2" , "Mediatek Dimensity 8100 Max")
                        .putExtra("icon3" , R.drawable.camera)
                        .putExtra("text3" , "Front - 32MP \n Rear - 50MP + 8MP + 2MP")
                        .putExtra("icon4" , R.drawable.battery)
                        .putExtra("text4" , "4500 mAh"));
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity() , Detailed.class)
                        .putExtra("na" , name2.getText().toString())
                        .putExtra("pri" , price2.getText().toString())
                        .putExtra("img" , "https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/nord2t.jpg?alt=media&token=7ed10ceb-7f84-49ba-a6b8-447727a58210")
                        .putExtra("icon1" , R.drawable.ram )
                        .putExtra("text1" , "12 GB RAM | 256 GB ROM")
                        .putExtra("icon2" , R.drawable.cpu)
                        .putExtra("text2" , "Octa Core 2.4 GHz")
                        .putExtra("icon3" , R.drawable.camera)
                        .putExtra("text3" , "Front - 32MP \n Rear - 50MP + 8MP")
                        .putExtra("icon4" , R.drawable.battery)
                        .putExtra("text4" , "4500 mAh"));
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity() , Detailed.class)
                        .putExtra("na" , name3.getText().toString())
                        .putExtra("pri" , price3.getText().toString())
                        .putExtra("img" , "https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/iphone.png?alt=media&token=44c03e68-2bfd-4433-991d-82cf071dc121")
                        .putExtra("icon1" , R.drawable.ram )
                        .putExtra("text1" , "64 GB ROM")
                        .putExtra("icon2" , R.drawable.cpu)
                        .putExtra("text2" , "A 14 Bionic Chip with Next Generation Neural Engine")
                        .putExtra("icon3" , R.drawable.camera)
                        .putExtra("text3" , "Front - 12MP \n Rear - 12MP + 12MP")
                        .putExtra("icon4" , R.drawable.battery)
                        .putExtra("text4" , "3650 mAh"));
            }
        });
    }

    private void initRecyclerView() {

        // For Category List
        RecyclerView rec_category = requireActivity().findViewById(R.id.rec_category);
        layoutManager = new LinearLayoutManager(requireActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rec_category.setLayoutManager(layoutManager);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), list);
        rec_category.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // For Popularity List
        RecyclerView popular_rec = requireActivity().findViewById(R.id.popular_rec);
        gridLayoutManager = new GridLayoutManager(requireActivity() , 2 , GridLayoutManager.VERTICAL , false);
        popular_rec.setLayoutManager(gridLayoutManager);
        PopularityAdapter popularityAdapter = new PopularityAdapter(requireActivity() , models , this);
        popular_rec.setAdapter(popularityAdapter);
        popularityAdapter.notifyDataSetChanged();
    }

    private void initData() {
        // Categories
        list = new ArrayList<>();
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/locker.png?alt=media&token=55f738d0-986c-45f7-ad5e-5b48ff50c5a8" , "Cabinet"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/baseball-cap.png?alt=media&token=f3653715-5f82-41be-9703-04a33bad9837" , "Cap"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/fan%20(1).png?alt=media&token=d0f2a442-7a22-4212-b9ed-bc387092644c" , "Stand Fan"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/furniture%20(1).png?alt=media&token=cf2edb7c-db8a-4e9e-8853-a4c54de32227" , "Furniture"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/meat-grinder.png?alt=media&token=ee4aa98f-c58c-458b-a714-bfbefcb62c12" , "Grinder"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/keyboard%20(1).png?alt=media&token=6dce07db-4444-4d34-8617-e6aa00f172d0" , "Keyboard"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/smartphone.png?alt=media&token=2d5867c5-f8cf-47fd-8f39-dfdd2b224f1e" , "Mobile"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/monitor%20(1).png?alt=media&token=1224f55d-ffd6-4456-ba3b-30d01809ef88" , "Monitor"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/computer-mouse.png?alt=media&token=34b4dd56-d089-4a72-830f-c7a760ff6f57" , "Mouse"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/mouse-pad.png?alt=media&token=5ed05f28-c2fc-4ef6-9e2a-53f399cc1e95" , "Mouse Pad"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/oven%20(1).png?alt=media&token=4cadb33b-b1c0-4678-97e4-8ba0320b00f5" , "Oven"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/jogger-pants.png?alt=media&token=76ef20ef-0fa3-4580-bbc2-34ebc8be6ac2" , "Pant"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/topBox.png?alt=media&token=886ec6e8-c7bf-4722-bf74-54adc2a2d9a9" , "Set Top Box"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/hawaiian-shirt.png?alt=media&token=843a60fc-0ace-44da-9dc6-88181a0aec18" , "Shirt"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/sneakers.png?alt=media&token=d3a89332-eeba-4de9-afa2-a72fdb38aa6b" , "Shoe"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/shorts%20(1).png?alt=media&token=cd27b840-5cc5-44fd-90f3-6e0e9b02fbd5" , "Shorts"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/speaker.png?alt=media&token=240e9bb1-fcbf-4f2c-b501-6596386177e4" , "Speaker"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/glasses.png?alt=media&token=12cc0ab2-c7ae-4794-9067-cb8e0e888ac1" , "Specs"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/suit%20(1).png?alt=media&token=34cb09cd-f70a-4efb-b50d-3b5f3ae0ae81" , "Suit"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/toaster%20(1).png?alt=media&token=dfbbd2df-be73-4c2a-89da-1ef4f036b07e" , "Toaster"));
        list.add(new CategoryModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/smart-tv.png?alt=media&token=0f841fd9-a379-4da4-a38b-a1e21723f3b2" , "TV"));

        // Popularity
        models = new ArrayList<>();
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/nothing.jpg?alt=media&token=33cd90e1-6d58-43fc-8dde-f8f4cb015e80" , "Nothing Phone" , "Rs 34000" , R.drawable.ram , "12 GB RAM | 256 GB ROM" , R.drawable.cpu , "Qualcomm Snapdragon 778G+" , R.drawable.camera , "50MP + 50MP | 16MP Front Camera" , R.drawable.battery , "4500 mAh "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/s22.png?alt=media&token=90c4c74b-be0a-475c-b78e-ac66ebfcb5fe" , "Samsung S22 Ultra" , "Rs 92790", R.drawable.ram , "12 GB RAM | 256 GB ROM" , R.drawable.cpu , "Octa Core Processor" , R.drawable.camera , "108MP + 12MP + 10MP + 10MP | 40MP Front Camera" , R.drawable.battery , "5000 mAh "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/vivox80plus.png?alt=media&token=ee284855-7135-4c1d-a9f8-5b1cf8a7caee" , "Vivo X80 Plus Pro" , "Rs 79999", R.drawable.ram , "12 GB RAM | 256 GB ROM" , R.drawable.cpu , "4700 mAhQualcomm Snapdragon 8 Gen" , R.drawable.camera , "50MP + 48MP + 12MP + 8MP | 32MP Front Camera" , R.drawable.battery , "4700 mAh"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/onplus10pro.webp?alt=media&token=139d55ab-3e61-48bf-9aa5-ac985991b0e4" , "One Plus 10 Pro" , "Rs 61999", R.drawable.ram , "8 GB RAM | 128 GB ROM" , R.drawable.cpu , "Dual Core 2.4GHz" , R.drawable.camera , "48MP Rear Camera" , R.drawable.battery , "5000 mAh "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/fold.png?alt=media&token=8a5f7efb-c737-4cb3-bb0d-e4fa90b3b893" , "Samsung Galaxy Z" , "Rs 139999", R.drawable.ram , "8 GB RAM | 256 GB ROM" , R.drawable.cpu , "Qualcomm Snapdragon 888 Octa-Core" , R.drawable.camera , "12MP + 12MP | 10MP Front Camera" , R.drawable.battery , "3300 mAh  "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/mac.png?alt=media&token=ae57485e-8b28-4a4e-98c1-54b3832dd338" , "i-Mac Book Air" , "Rs 124990", R.drawable.ram , "16 GB RAM | 16 GB SSD" , R.drawable.cpu , "Apple M1 Chip" , R.drawable.camera , "720p FaceTime HD Webcam" , R.drawable.resolution , "2560 x 1600 Pixels "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/rog%20Zephyrus.png?alt=media&token=cf8cb4c3-8738-4361-a6b4-5748acb4a89f" , "Asus Rog Zephyrus" , "Rs 391990", R.drawable.ram , "8 GB RAM | 512 GB SSD" , R.drawable.cpu , "AMD Ryzen™ 7 5800H" , R.drawable.resolution , "1920 x 1080 pixel" , R.drawable.graphics , "NVIDIA GeForce GTX 1650"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/gf63.jpg?alt=media&token=912bc64f-6532-4a16-8f26-af9cdec3fb30" , "MSI GF63" , "Rs 54990", R.drawable.ram , "8 GB RAM | 1 TB HDD" , R.drawable.cpu , "Core i5 9th Gen 9300H" , R.drawable.camera , "HD Type Webcam 720p at 30fps" , R.drawable.graphics , "NVIDIA GeForce GTX 1650 Ti Max Q"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/leopard.jpg?alt=media&token=464dcc5f-7081-459e-8f1b-c89092fb2de4" , "MSI Leopard" , "Rs 156090", R.drawable.ram , "16 GB RAM | 256 GB SSD" , R.drawable.cpu , "Core i7 10th Gen 10750H" , R.drawable.resolution , "1920 x 1080 Pixels" , R.drawable.graphics , "NVIDIA GeForce RTX 2060"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/alienware.png?alt=media&token=c9b1b138-36b3-4981-a70a-f721c07fe045" , "AlienWare m15 R3" , "Rs 364989", R.drawable.ram , "32 GB RAM | 1 TB SSD" , R.drawable.cpu , "Intel Core i9 10th Gen 10980HK" , R.drawable.resolution , "HD Webcam" , R.drawable.graphics , "NVIDIA GeForce RTX 2080 with Max-Q"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/rolex.png?alt=media&token=9f2fdad5-93f7-46fa-a54b-b2e4abedf3ce" , "Rolex Cosmograph" , "Rs 6999", R.drawable.watch , "Analog" , R.drawable.construction , "Automatic" , R.drawable.color , "Silver" , R.drawable.waranty , "2 Years"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/titan.png?alt=media&token=0030e740-50da-454c-a9fa-d9dda20cc02e" , "Titan Smart 2" , "Rs 7495", R.drawable.watch , "Digital" , R.drawable.construction , "Automatic" , R.drawable.color , "Black" , R.drawable.waranty , "1 Year "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/fasttrack.png?alt=media&token=6b2ca6f3-cab2-42f6-b568-5ee121f4ef14" , "Fastrack Reflex" , "Rs 3745", R.drawable.watch , "Digital" , R.drawable.construction , "Automatic" , R.drawable.color , "Blue" , R.drawable.waranty , "1 Year"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/sonata.png?alt=media&token=9c2c1a37-7f6e-46a5-a559-6ca341c7f36e" , "Sonata Plus Dt-2" , "Rs 2635", R.drawable.watch , "Analog" , R.drawable.construction , "Automatic" , R.drawable.color , "Pink" , R.drawable.waranty , "3 years"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/hugo%20boss.png?alt=media&token=06bc21dd-7f2e-4f26-9add-daf8181f96ca" , "Hugo Boss" , "Rs 11000", R.drawable.sleeves , "Short Sleeves" , R.drawable.collar , "Round Neck" , R.drawable.fabric , "Cotton" , R.drawable.tshirt , "S"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/levis.png?alt=media&token=60803dc7-1670-41f6-98af-1c13fa7b3587" , "Levis" , "Rs 769", R.drawable.sleeves , "Short Sleeves" , R.drawable.collar , "Round Neck" , R.drawable.fabric , "Cotton" , R.drawable.tshirt , "M "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/stop.jpg?alt=media&token=305d014d-c2d6-4ac9-ac64-bd53c3a1eeb4" , "Stop" , "Rs 399", R.drawable.sleeves , "Short Sleeves" , R.drawable.collar , "Round Neck" , R.drawable.fabric , "Silk" , R.drawable.tshirt , "M "));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/pepe.png?alt=media&token=61aa51ab-77b1-461f-bb6b-b32f9e5ab8c7" , "Pepe" , "Rs 1399", R.drawable.sleeves , "Short Sleeves" , R.drawable.collar , "Round Neck" , R.drawable.fabric , "Cotton" , R.drawable.tshirt , "L"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/leecopper.png?alt=media&token=4686e6c0-0927-4732-abab-af9255db7a71" , "LEE COOPER" , "Rs 599", R.drawable.sleeves , "Short Sleeves" , R.drawable.collar , "Round Bottom" , R.drawable.fabric , "Silk" , R.drawable.tshirt , "4L"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/johnjacobs.png?alt=media&token=ff3d7244-eac4-45c8-b5b1-bc61b42409ce" , "John Jacobs" , "Rs 4480", R.drawable.sunglasses , "Crystal White" , R.drawable.aperture, "Medium" , R.drawable.lens , "Silver" , R.drawable.notebook , "Retro Square"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/vicentchase.png?alt=media&token=26524b83-0c13-41e2-a35f-173b3000c4f4" , "Vicent Chase" , "Rs 6999", R.drawable.sunglasses , "Crystal White" , R.drawable.aperture, "Large" , R.drawable.lens , "Black" , R.drawable.notebook , "Retro Square"));
        models.add(new PopularModel("https://firebasestorage.googleapis.com/v0/b/buymenow-468e2.appspot.com/o/rayban.png?alt=media&token=f6e4d556-3857-49da-94f1-d1b447971bf9" , "Ray Ban Gold" , "Rs 11865", R.drawable.sunglasses , "Coal Black" , R.drawable.aperture , "Small" , R.drawable.lens , "Gold" , R.drawable.notebook , "Aviator"));

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity() , Detailed.class);

        intent.putExtra("img" , models.get(position).getImage());
        intent.putExtra("na" , models.get(position).getName());
        intent.putExtra("pri" , models.get(position).getPrice());
        intent.putExtra("icon1" , models.get(position).getIcon1());
        intent.putExtra("text1" , models.get(position).getText1());
        intent.putExtra("icon2" , models.get(position).getIcon2());
        intent.putExtra("text2" , models.get(position).getText2());
        intent.putExtra("icon3" , models.get(position).getIcon3());
        intent.putExtra("text3" , models.get(position).getText3());
        intent.putExtra("icon4" , models.get(position).getIcon4());
        intent.putExtra("text4" , models.get(position).getText4());

        startActivity(intent);
    }
}