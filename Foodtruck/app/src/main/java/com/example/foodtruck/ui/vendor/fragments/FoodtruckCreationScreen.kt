package com.example.foodtruck.ui.vendor.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.foodtruck.R
import com.example.foodtruck.data.source.local.model.Foodtruck
import com.example.foodtruck.data.source.local.model.Menu
import com.example.foodtruck.util.createAlert
import com.example.foodtruck.util.setVisibilityToGone
import com.example.foodtruck.util.setVisibilityToVisible
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.fullscreen_dialog_foodtruck_creation.*
import kotlinx.android.synthetic.main.hourly_grid_layout.*

class FoodtruckCreationScreen: DialogFragment(), Toolbar.OnMenuItemClickListener, MenuCreationScreen.MenuItemReceiver {

    lateinit var listener: FoodtruckReceiver
    private var createdMenu: Menu? = null
    private var currentPosition: Int? = null

    interface FoodtruckReceiver{
        fun receiveFoodtruck(foodtruck: Foodtruck, tag: String, position: Int?)
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

        setFoodtruckLayout()

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
                                    btn_edit_menu.setVisibilityToGone()
                                    btn_delete_menu.setVisibilityToGone()}, { d, i-> }).show()
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

    private fun setFoodtruckLayout() {
        val bundle = arguments
        if(bundle != null){
            val foodtruck = bundle.get("foodTruckToEdit") as Foodtruck
            et_foodtruck_name.setText(foodtruck.name)
            et_foodtruck_model.setText(foodtruck.model)
            foodtruck.menu?.let{
                createdMenu = it
                btn_create_menu.setVisibilityToGone()
                btn_edit_menu.setVisibilityToVisible()
                btn_delete_menu.setVisibilityToVisible()
            }
            currentPosition = bundle.get("foodTruckToEditPosition") as Int
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
            val foodtruck = Foodtruck(foodtruckName, foodtruckModel, 0.0, 0.0, createdMenu)
            listener.receiveFoodtruck(foodtruck, tag!!, currentPosition)
            dismiss()
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)

        super.onCreate(savedInstanceState)
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
        view.findViewById<MaterialSpinner>(R.id.spinner1)
            .setItems(
        "00:00",
        "01:00",
        "02:00",
        "03:00",
        "04:00",
        "05:00",
        "06:00",
        "07:00",
        "08:00",
        "09:00",
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
        "19:00",
        "20:00",
        "21:00",
        "22:00",
        "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner2)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner3)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner4)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner5)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner6)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner7)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner8)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner9)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner10)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner11)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner12)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner13)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        view.findViewById<MaterialSpinner>(R.id.spinner14)
            .setItems(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00")
        return view
    }
}