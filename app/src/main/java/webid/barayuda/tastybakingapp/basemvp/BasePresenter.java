package webid.barayuda.tastybakingapp.basemvp;

/**
 * Created by BARAYUDA on 8/27/2017.
 */

public interface BasePresenter<T extends BaseView> {
    void onAttach(T BaseView);
    void onDetach();
}
