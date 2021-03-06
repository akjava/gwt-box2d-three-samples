package org.jbox2d.gwt.showcase.client.rendering;

import org.jbox2d.gwt.showcase.client.example.ApplyForce;
import org.jbox2d.gwt.showcase.client.example.BlobTest4;
import org.jbox2d.gwt.showcase.client.example.BodyTypes;
import org.jbox2d.gwt.showcase.client.example.Breakable;
import org.jbox2d.gwt.showcase.client.example.Cantilever;
import org.jbox2d.gwt.showcase.client.example.Chain;
import org.jbox2d.gwt.showcase.client.example.CharacterCollision;
import org.jbox2d.gwt.showcase.client.example.CircleStress;
import org.jbox2d.gwt.showcase.client.example.CollisionFiltering;
import org.jbox2d.gwt.showcase.client.example.CollisionProcessing;
import org.jbox2d.gwt.showcase.client.example.CompoundShapesTest;
import org.jbox2d.gwt.showcase.client.example.ConfinedTest;
import org.jbox2d.gwt.showcase.client.example.ContinuousTest;
import org.jbox2d.gwt.showcase.client.example.DistanceTest;
import org.jbox2d.gwt.showcase.client.example.DominoTest;
import org.jbox2d.gwt.showcase.client.example.DominoTower;
import org.jbox2d.gwt.showcase.client.example.DynamicTreeTest;
import org.jbox2d.gwt.showcase.client.example.EdgeShapes;
import org.jbox2d.gwt.showcase.client.example.Gears;
import org.jbox2d.gwt.showcase.client.example.LineJointTest;
import org.jbox2d.gwt.showcase.client.example.OneSidedTest;
import org.jbox2d.gwt.showcase.client.example.PolyShapes;
import org.jbox2d.gwt.showcase.client.example.PrismaticTest;
import org.jbox2d.gwt.showcase.client.example.Pulleys;
import org.jbox2d.gwt.showcase.client.example.PyramidTest;
import org.jbox2d.gwt.showcase.client.example.RayCastTest;
import org.jbox2d.gwt.showcase.client.example.RevoluteTest;
import org.jbox2d.gwt.showcase.client.example.SensorTest;
import org.jbox2d.gwt.showcase.client.example.ShapeEditing;
import org.jbox2d.gwt.showcase.client.example.SliderCrankTest;
import org.jbox2d.gwt.showcase.client.example.SphereStack;
import org.jbox2d.gwt.showcase.client.example.TheoJansen;
import org.jbox2d.gwt.showcase.client.example.VaryingFrictionTest;
import org.jbox2d.gwt.showcase.client.example.VaryingRestitution;
import org.jbox2d.gwt.showcase.client.example.VerticalStack;
import org.jbox2d.gwt.showcase.client.example.Web;
import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.framework.StartExampleEvent;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public class ExamplesTreeModel implements TreeViewModel, SelectionChangeEvent.Handler {

  private final EventBus eventBus;
  private final ExampleCell exampleCell = new ExampleCell();
  private final SingleSelectionModel<BaseExample> selectionModel;
  private final ListDataProvider<Category> categoriesProvider;

  public static ExamplesTreeModel create(EventBus eventBus) {
    ExamplesTreeModel treeModel = new ExamplesTreeModel(eventBus);

    // featured examples
    Category category = treeModel.new Category("Featured Examples");
    category.addExample(new DominoTest());
    category.addExample(new CompoundShapesTest());
    category.addExample(new BlobTest4());
    category.addExample(new TheoJansen());
    treeModel.addCategory(category);
    
    // general collision watching...
    category = treeModel.new Category("Collision Watching");
    category.addExample(new DominoTower());
    category.addExample(new CircleStress());
    category.addExample(new VaryingRestitution());
    category.addExample(new VaryingFrictionTest());
    category.addExample(new SphereStack());
    category.addExample(new CompoundShapesTest());
    category.addExample(new DominoTest());
    category.addExample(new VerticalStack());
    category.addExample(new PyramidTest());
    treeModel.addCategory(category);

    // more interactive..
    category = treeModel.new Category("Interactive");
    category.addExample(new ShapeEditing());
    category.addExample(new Breakable());
    category.addExample(new OneSidedTest());
    category.addExample(new PolyShapes());
    category.addExample(new BodyTypes());
    category.addExample(new CharacterCollision());
    category.addExample(new ApplyForce());
    treeModel.addCategory(category);

    // processing/filtering
    category = treeModel.new Category("Processing / Filtering");
    category.addExample(new CollisionFiltering());
    category.addExample(new CollisionProcessing());
    category.addExample(new SensorTest());
    treeModel.addCategory(category);

    // joints
    category = treeModel.new Category("Joints");
    category.addExample(new PrismaticTest());
    category.addExample(new RevoluteTest());
    category.addExample(new Pulleys());
    category.addExample(new LineJointTest());
    category.addExample(new Gears());
    category.addExample(new Web());
    category.addExample(new Chain());
    category.addExample(new Cantilever());
    category.addExample(new SliderCrankTest());
    category.addExample(new BlobTest4());
    category.addExample(new TheoJansen());
    treeModel.addCategory(category);

    // ccd
    category = treeModel.new Category("CCD");
    category.addExample(new ContinuousTest());
    category.addExample(new ConfinedTest());
    treeModel.addCategory(category);

    // raycast
    category = treeModel.new Category("Raycast");
    category.addExample(new RayCastTest());
    category.addExample(new EdgeShapes());
    treeModel.addCategory(category);

    // misc
    category = treeModel.new Category("Misc");
    category.addExample(new DynamicTreeTest());
    category.addExample(new DistanceTest());
    treeModel.addCategory(category);

    return treeModel;
  }

  private ExamplesTreeModel(EventBus eventBus) {
    this.eventBus = eventBus;
    selectionModel = new SingleSelectionModel<BaseExample>();
    categoriesProvider = new ListDataProvider<Category>();
    selectionModel.addSelectionChangeHandler(this);
  }

  public void addCategory(Category category) {
    categoriesProvider.getList().add(category);
  }

  class ExampleCell extends AbstractCell<BaseExample> {

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context, BaseExample value,
        SafeHtmlBuilder sb) {
      sb.appendEscaped(value.getTestName());
    }
  }
  
  public SingleSelectionModel<BaseExample> getSelectionModel(){
    return selectionModel;
  }

  @Override
  public <T> NodeInfo<?> getNodeInfo(T value) {
    if (value == null) {
      // Return the top level categories.
      return new DefaultNodeInfo<Category>(categoriesProvider, new CategoryCell());
    } else if (value instanceof Category) {
      // Return the examples within the category.
      Category category = (Category) value;
      return category.getNodeInfo();
    }
    return null;
  }

  @Override
  public boolean isLeaf(Object value) {
    return value != null && !(value instanceof Category);
  }

  public class Category {
    private final ListDataProvider<BaseExample> exampleProvider;
    private final String name;
    private NodeInfo<BaseExample> nodeInfo;

    public Category(String name) {
      this.name = name;
      this.exampleProvider = new ListDataProvider<BaseExample>();
      this.nodeInfo = new DefaultNodeInfo<BaseExample>(exampleProvider, exampleCell,
          selectionModel, null);
    }

    public void addExample(BaseExample example) {
      exampleProvider.getList().add(example);
    }

    public ListDataProvider<BaseExample> getExampleProvider() {
      return exampleProvider;
    }

    public String getName() {
      return name;
    }

    public NodeInfo<BaseExample> getNodeInfo() {
      return nodeInfo;
    }
  }

  private static class CategoryCell extends AbstractCell<Category> {
    @Override
    public void render(Context context, Category value, SafeHtmlBuilder sb) {
      if (value != null) {
        sb.appendEscaped(value.getName());
      }
    }
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    BaseExample example = selectionModel.getSelectedObject();
    eventBus.fireEvent(new StartExampleEvent(example));
  }
}
