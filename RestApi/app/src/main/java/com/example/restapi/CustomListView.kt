package com.example.restapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.example.restapi.api.ServiceRepositary
import com.example.restapi.models.CustomListAdapter
import com.example.restapi.models.UserRepositary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CustomListView : Fragment() {

    var customelistview:ListView? = null;
    var rootview:View? = null;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.customview_fragment, container, false)

        customelistview = rootview!!.findViewById<ListView>(R.id.customelistview)

        // Inflate the layout for this fragment
        var retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        var retro = retrofit.create(ServiceRepositary::class.java)
        var getcall = retro.listRepos()

        if (getcall != null) {
            getcall.enqueue(object : Callback<List<UserRepositary?>?> {


                override fun onFailure(call: Call<List<UserRepositary?>?>, t: Throwable) {
                    Log.d("Tharun Error", t.toString());
                }

                override fun onResponse(
                        call: Call<List<UserRepositary?>?>,
                        response: Response<List<UserRepositary?>?>
                ) {
                    var list = response.body()
                    if (list != null) {
                        // Log.d("Size", list.size.toString())
                        var titlearray = Array<String>(list.size.toInt()) { " " };
                        var idarray = Array<Int>(list.size.toInt()) { 0 };


                        for (user: UserRepositary? in list!!) {
                            titlearray[user?.id?.toInt()?.minus(1)!!] = user?.title.toString()
                            idarray[user.id?.toInt()?.minus(1)!!] = user?.id!!.toInt()
                              Log.d("Tharun Tilte", user?.title.toString())
                        }
                         val MyListAdapter = activity?.let { CustomListAdapter(it,idarray,titlearray) }
                         customelistview?.adapter = MyListAdapter


                    }
                }
            })
        }

        return rootview;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}