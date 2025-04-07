import { Injectable } from '@angular/core';
import { NodeWorkspace } from '../models/survey_model';
import { HttpClient } from '@angular/common/http';
import { WorkspaceGetDto } from '../models/node_model';
import { environment } from '../../environments/environment';
import { Observable, tap } from 'rxjs';
import { SurveyTreeService } from './survey-tree.service';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NodeService {

  private treeDataSubject = new Subject<NodeWorkspace[]>();

  treeData$ = this.treeDataSubject.asObservable();

  private treeData: NodeWorkspace[] = [];

  dataMap = new Map<number, NodeWorkspace>();
  data: NodeWorkspace[] = [];

  private apiUrl = environment.surveyApiUrl;

  constructor(private http: HttpClient, private treeService: SurveyTreeService) {
   
  }

  getTree(dataGet: NodeWorkspace[]): NodeWorkspace[]{
    console.log("pozvano")
    this.data = dataGet
      this.data.forEach(node => {
        this.dataMap.set(node.id, node); 
        console.log("node", node)
      });
      console.log("kraj")

      console.log(this.dataMap)

    // Build tree structure
    this.treeData = Array.from(this.dataMap.values()).filter(node => node.parentId === null);
    this.treeData.forEach(node => this.buildTree(node));

    this.treeData.forEach(node => {
      let cld: number[] = []
      node.level = 1
      node.children?.forEach(child =>{
        child.level = node.level+1
        cld.push(child.id)
      })
      this.treeService.activation[node.id]={active:false,children:cld,surveyId:node.surveyId}
      this.recursivelyAddChild(node.children)
    });

    console.log("tree ", this.treeData)
    this.treeDataSubject.next(this.treeData);

    return this.treeData
}

recursivelyAddChild(children: NodeWorkspace[] | undefined): void {
if(children == undefined) return
if (children.length > 0) {
children.forEach(child => {
  let cld: number[] = []
    child.children?.forEach(chd =>{
      cld.push(chd.id)
    })
    this.treeService.activation[child.id]={active:false,children:cld,surveyId:child.surveyId}

  this.recursivelyAddChild(child.children);
});
}
}

buildTree(node: NodeWorkspace): void {
const children = Array.from(this.dataMap.values()).filter(child => child.parentId === node.id);
if (children.length > 0) {
node.children = children;
node.active = false;
children.forEach(child => {this.buildTree(child); child.active = false;});
}
} 

getWorkspace(userId: number, organizationId: number): Observable<NodeWorkspace[]> {
  let workspaceDto: WorkspaceGetDto = {
    userId: userId,
    organizationId: organizationId,
    domain: "Education"
  };

  console.log("Sending request with workspaceDto:");
  console.log(workspaceDto);

  return this.http.post<NodeWorkspace[]>(`${this.apiUrl}/workspace/workspace`, workspaceDto)
    .pipe(
      tap((response: NodeWorkspace[]) => {
        console.log("Received data in getWorkspace:");
        console.log(response);
        
        this.getTree(response)

      })
    );
}

}
