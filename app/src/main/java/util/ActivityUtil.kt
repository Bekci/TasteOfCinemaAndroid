package util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ActivityUtil {

    fun changeFragment(fragment: Fragment, tag: String? = null, manager: FragmentManager, container: Int, addToStack: Boolean) {
        manager.apply{
            var trans= beginTransaction()
                .replace(container, fragment, tag)
            if(addToStack)
                trans = trans.addToBackStack(tag)
            trans.commit()
        }
    }
}