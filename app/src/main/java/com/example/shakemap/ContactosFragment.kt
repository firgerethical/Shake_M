package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerContactos)

        val contactos = listOf(
            ContactoEmergencia("Emergencias", "911", R.drawable.nueveonce),
            ContactoEmergencia("Cruz Roja", "065", R.drawable.cruzroja),
            ContactoEmergencia("Policia Federal", "088", R.drawable.policia),
            ContactoEmergencia("Locatel", "5556581111", R.drawable.locatel),
            ContactoEmergencia("Protección Civil", "5551280000", R.drawable.proteccioncivil),
            ContactoEmergencia("Bomberos", "068", R.drawable.bomberos),
            ContactoEmergencia("Guardia Nacional", "088", R.drawable.guardianacional)
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ContactoAdapter(requireContext(), contactos)
        recyclerView.adapter = adapter
    }
}
