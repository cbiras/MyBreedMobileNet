package ro.upt.sma.MyBreedMobileNet.view

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_item.view.*
import ro.upt.sma.MyBreedMobileNet.R
import ro.upt.sma.MyBreedMobileNet.domain.Recognized
import java.net.URI

class RecognizedRecyclerViewAdapter(
    private val breedList: List<Recognized>,
    private val onDeleteClick: (Recognized) -> (Unit)
) : RecyclerView.Adapter<RecognizedRecyclerViewAdapter.BreedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return BreedViewHolder(view)
    }

    override fun getItemCount(): Int {

        return breedList.size
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {

        holder.bind(breedList[position])
    }




    inner class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.ibt_delete.setOnClickListener { onDeleteClick(breedList[adapterPosition]) }
        }

        fun bind(breed: Recognized) {

            itemView.findViewById<TextView>(R.id.txv_content).text = breed.description
            setImage(itemView,breed)

        }
        //I know this is really  bad programming, but I had an error trying to give a custom string to the setImagePath function, and invested too much time trying to solve it, with no solution
        fun setImage(itemView: View,breed: Recognized){
            when(breed.description){
                "affenpinscher" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.affenpinscher)
                "afghan_hound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.afghan_hound)
                "african_hunting_dog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.african_hunting_dog)
                "airedale" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.airedale)
                "american_staffordshire_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.american_staffordshire_terrier)
                "appenzeller" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.appenzeller)
                "australian_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.australian_terrier)
                "basenji" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.basenji)
                "basset" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.basset)
                "beagle" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.beagle)
                "bedlington_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bedlington_terrier)
                "bernese_mountain_dog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bernese_mountain_dog)
                "black-and-tan_coonhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.tan_coonhound)
                "blenheim_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.blenheim_spaniel)
                "bloodhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bloodhound)
                "bluetick" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bluetick)
                "border_collie" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.collie)
                "border_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.border_terrier)
                "borzoi" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.borzoi)
                "boston_bull" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.boston_bull)
                "bouvier_des_flandres" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bouvier_des_flandres)
                "boxer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.boxer)
                "brabancon_griffon" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.brabancon_griffon)
                "briard" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.briard)
                "brittany_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.brittany_spaniel)
                "bull_mastiff" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.bull_mastiff)
                "cairn" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.cairn)
                "cardigan" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.cardigan)
                "chesapeake_bay_retriever" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.chesapeake_bay_retriever)
                "chihuahua" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.chihuahua)
                //30
                "chow" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.chow)
                "clumber" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.clumber)
                "cocker_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.cocker_spaniel)
                "collie" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.collie)
                "curly-coated_retriever" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.curly_coated_retriever)
                "dandie_dinmont" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.dandie_dinmont)
                "dhole" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.dhole)
                "dingo" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.dingo)
                "doberman" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.doberman)
                "english_foxhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.english_foxhound)
                "english_setter" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.english_setter)
                "english_springer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.english_springer)
                "entlebucher" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.entlebucher)
                "eskimo_dog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.eskimo_dog)
                "flat-coated_retriever" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.flat_coated_retriever)
                "french_bulldog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.french_bulldog)
                "german_shepherd" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.german_shepherd)
                "german_short-haired_pointer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.german_short_haired_pointer)
                "giant_schnauzer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.giant_schnauzer)
                "golden_retriever" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.golden_retriever)
                "gordon_setter" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.gordon_setter)
                "great_dane" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.great_dane)
                "great_pyrenees" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.great_pyrenees)
                "greater_swiss_mountain_dog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.greater_swiss_mountain_dog)
                "groenendael" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.groenendael)
                "ibizan_hound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.ibizan_hound)
                "irish_setter" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.irish_setter)
                "irish_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.irish_terrier)
                "irish_water_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.irish_water_spaniel)
                "irish_wolfhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.irish_wolfhound)
                //60
                "italian_greyhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.italian_greyhound)
                "japanese_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.japanese_spaniel)
                "keeshond" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.keeshond)
                "kelpie" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.kelpie)
                "kerry_blue_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.kerry_blue_terrier)
                "komondor" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.komondor)
                "kuvasz" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.kuvasz)
                "labrador_retriever" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.labrador_retriever)
                "lakeland_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.lakeland_terrier)
                "leonberg" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.leonberg)
                "lhasa" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.lhasa)
                "malamute" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.malamute)
                "malinois" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.malinois)
                "maltese_dog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.maltese_dog)
                "mexican_hairless" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.mexican_hairless)
                "miniature_pinscher" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.miniature_pinscher)
                "miniature_poodle" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.miniature_poodle)
                "miniature_schnauzer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.miniature_schnauzer)
                "newfoundland" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.newfoundland)
                "norfolk_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.norfolk_terrier)
                "norwegian_elkhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.norwegian_elkhound)
                "norwich_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.norwich_terrier)
                "old_english_sheepdog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.old_english_sheepdog)
                "otterhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.otterhound)
                "papillon" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.papillon)
                "pekinese" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.pekinese)
                "pembroke" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.pembroke)
                "pomeranian" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.pomeranian)
                "pug" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.pug)
                "redbone" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.redbone)
                //90
                "rhodesian_ridgeback" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.rhodesian_ridgeback)
                "rottweiler" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.rottweiler)
                "saint_bernard" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.saint_bernard)
                "saluki" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.saluki)
                "samoyed" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.samoyed)
                "schipperke" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.schipperke)
                "scotch_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.scotch_terrier)
                "scottish_deerhound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.scottish_deerhound)
                "sealyham_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.sealyham_terrier)
                "shetland_sheepdog" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.shetland_sheepdog)
                "shih-tzu" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.shih_tzu)
                "siberian_husky" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.siberian_husky)
                "silky_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.silky_terrier)
                "soft-coated_wheaten_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.soft_coated_wheaten_terrier)
                "staffordshire_bullterrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.staffordshire_bullterrier)
                "standard_poodle" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.standard_poodle)
                "standard_schnauzer" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.standard_schnauzer)
                "sussex_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.sussex_spaniel)
                "tibetan_mastiff" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.tibetan_mastiff)
                "tibetan_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.tibetan_terrier)
                "toy_poodle" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.toy_poodle)
                "toy_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.toy_terrier)
                "vizsla" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.vizsla)
                "walker_hound" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.walker_hound)
                "weimaraner" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.weimaraner)
                "welsh_springer_spaniel" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.welsh_springer_spaniel)
                "west_highland_white_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.west_highland_white_terrier)
                "whippet" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.whippet)
                "wire-haired_fox_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.wire_haired_fox_terrier)
                "yorkshire_terrier" -> itemView.findViewById<ImageView>(R.id.img_content).setImageResource(R.drawable.yorkshire_terrier)
                //120
            }
        }

    }

}