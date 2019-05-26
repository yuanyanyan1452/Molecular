package organicsView;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class C2H6App extends Application {

    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform moleculeGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -2000;//设置的大一点，camera就远一点，看到的视图就大一点
    private static final double CAMERA_INITIAL_X_ANGLE = 0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 600.0;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
    //   private void buildScene() {
    //       root.getChildren().add(world);
    //   }
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX); 
                mouseDeltaY = (mousePosY - mouseOldY); 
                
                double modifier = 1.0;
                
                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                } 
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }     
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);  
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);  
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);  
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);  
                }
            }
        });
    }
    
    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;
                }
            }
        });
    }
    
    private void buildMolecule() {
        //======================================================================
        // THIS IS THE IMPORTANT MATERIAL FOR THE TUTORIAL
        //======================================================================

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.DARKGREEN);
        

        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);

        Xform moleculeXform = new Xform();
        LinkedList<Xform> bondXforms=new LinkedList<Xform>();//装化学键的form，7个
        LinkedList<Xform> moleXforms=new LinkedList<Xform>();//装碳氢原子的form，8个
        for(int i=1;i<=7;i++) {
        	bondXforms.add(new Xform());
        }
        for(int i=1;i<=8;i++) {
        	moleXforms.add(new Xform());
        }
        //建立8个原子和7个化学键
        LinkedList<Sphere> spheres=new LinkedList<Sphere>();
        LinkedList<Cylinder> bonds=new LinkedList<Cylinder>();
        for(int i=1;i<=8;i++) {
        	if(i==1||i==2) {
        		Sphere c=new Sphere(40);
        		c.setMaterial(redMaterial);
        		spheres.add(c);//两个碳原子
        	}else {
        		Sphere h=new Sphere(30);
        		h.setMaterial(whiteMaterial);
        		spheres.add(h);//6个氢原子
        	}
        }
        for(int i=1;i<=7;i++) {
        	if(i==1) {
        		Cylinder CCBond = new Cylinder(5, 154);
                CCBond.setMaterial(greyMaterial);
            	bonds.add(CCBond);//碳碳键
        	}else {
        		Cylinder CHBond = new Cylinder(5, 109);
                CHBond.setMaterial(greyMaterial);
            	bonds.add(CHBond);//碳氢键
        	}
        }
        //安装所有的forms
        moleculeXform.getChildren().addAll(bondXforms);//7个
        moleculeXform.getChildren().addAll(moleXforms);//8个
        //所有的forms里面安装具体的组件（原子和化学键）
        for(int i=0;i<7;i++) {
        	bondXforms.get(i).getChildren().add(bonds.get(i));
        }
        for(int i=0;i<8;i++) {
        	moleXforms.get(i).getChildren().add(spheres.get(i));
        }
        //8原子坐标,两个碳+6个氢
        for(int i=0;i<8;i++) {
        	Xform temp=moleXforms.get(i);
        	if(i==0)		temp.setTranslate(0,0,0);
        	else if(i==1)	temp.setTranslate(0,154,0);
        	else if(i==2)	temp.setTranslate(-51, -109/3,51*Math.sqrt(3));
        	else if(i==3)	temp.setTranslate(102, -109/3,0);
        	else if(i==4)	temp.setTranslate(-51, -109/3,-51*Math.sqrt(3));
        	else {
        		Xform symmetry=moleXforms.get(i-3);
        		temp.setTranslate(0-(symmetry.t.getX()), 154-(symmetry.t.getY()),0-(symmetry.t.getZ()));
        	}
        }
        //键角度和移动坐标（原子坐标的一半）
        for(int i=0;i<7;i++) {
        	Xform bond=bondXforms.get(i);
        	if(i==0) {//碳碳键
        		bond.setTranslate(0, 154/2,0);
        	}else if(i==1) {
        		bond.setRotate(90,-30,19);
                bond.setTranslate(-51/2, -109/6,51*Math.sqrt(3)/2);
        	}else if(i==2) {
        		bond.setRotate(0,0,71);
                bond.setTranslate(51, -109/6,0);
        	}else if(i==3) {
        		bond.setRotate(90,30,19);
                bond.setTranslate(-51.0/2, -109.0/6,-51.0*Math.sqrt(3)/2);
        	}else  {
        		Xform tempBond=bondXforms.get(i-3);
        		Xform tempMole=moleXforms.get(i-2);
        		bond.setRotate(tempBond.rx.getAngle(),tempBond.ry.getAngle(),tempBond.rz.getAngle());
        		bond.setTranslate(0-(tempMole.t.getX())/2, 154-(tempMole.t.getY())/2,0-(tempMole.t.getZ())/2);//这里待解释
        	}
        }
        moleculeGroup.getChildren().add(moleculeXform);

        world.getChildren().addAll(moleculeGroup);
    }

    @Override
    public void start(Stage primaryStage) {
        
       // setUserAgentStylesheet(STYLESHEET_MODENA);
        System.out.println("start()");

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        // buildScene();
        buildCamera();
        buildAxes();
        buildMolecule();

        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene, world);
        handleMouse(scene, world);

        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setCamera(camera);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}