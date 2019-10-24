package com.example.foodtruck.ui.vendor.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Foodtruck
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.util.createAlert
import com.example.foodtruck.util.setVisibilityToGone
import com.example.foodtruck.util.setVisibilityToVisible
import kotlinx.android.synthetic.main.fullscreen_dialog_foodtruck_creation.*

class FoodtruckCreationScreen: DialogFragment(), Toolbar.OnMenuItemClickListener, MenuCreationScreen.MenuItemReceiver {

    private lateinit var listener: FoodtruckReceiver
    private var createdMenu: Menu? = null

    interface FoodtruckReceiver{
        fun receiveFoodtruck(foodtruck: Foodtruck)
    }

    override fun receivePotentialMenu(menu: Menu?) {
        menu?.let{
            createdMenu = it
            //successfully created a new menu, hide create button, show view and delete buttons
            btn_create_menu.setVisibilityToGone()
            btn_edit_menu.setVisibilityToVisible()
            btn_delete_menu.setVisibilityToVisible()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        top_toolbar.setNavigationOnClickListener {
            context!!.createAlert({d, i -> dismiss() }, {d, i-> }).show() //stay on the fragment
        }

        top_toolbar.setOnMenuItemClickListener(this)

        btn_create_menu.setOnClickListener {
            val m = MenuCreationScreen()
            m.listener = this
            m.show(fragmentManager!!, "menu creation")
        }

        btn_delete_menu.setOnClickListener {
            //Are you sure you are deleting the menu?
            context!!.createAlert({d, i -> createdMenu = null
                                    btn_create_menu.setVisibilityToVisible()
                                    btn_edit_menu.setVisibilityToGone()}, { d, i-> }).show()
        }

        btn_edit_menu.setOnClickListener {
            //we definitely have a menu, that we can load into the menu creation screen
            val m = MenuCreationScreen()
            m.listener = this
            val bundle = Bundle()
            bundle.putSerializable("menu_edit", createdMenu)
            m.arguments = bundle
            m.show(fragmentManager!!, "edit menu")
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val foodtruckName = et_foodtruck_name.text.toString()
        val foodtruckModel = et_foodtruck_model.text.toString()
        val mondayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_monday)
        val tuesdayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_tuesday)
        val wednesdayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_wednesday)
        val thursdayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_thursday)
        val fridayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_friday)
        val saturdayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_saturday)
        val sundayCheckbox = grid_layout.findViewById<CheckBox>(R.id.checkbox_sunday)

        if(foodtruckName == ""){
            textInputLayout.error = "Food truck must have a name."
        }

        if(foodtruckModel == ""){
            text_input_truck_model.error = "Food truck must have a model."
        }

        if(foodtruckName != "" && foodtruckModel != ""){
            //pass this data back to the activity
            val foodtruck = Foodtruck(foodtruckName, foodtruckModel, 0.0, 0.0)
            listener.receiveFoodtruck(foodtruck)
            dismiss()
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FoodtruckReceiver) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onStart() {
        super.onStart()

        if(dialog != null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            (dialog as Dialog).window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fullscreen_dialog_foodtruck_creation, container, false)
        return view
    }

}